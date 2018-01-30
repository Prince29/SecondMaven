package com.xsu.web.common.client;


import com.alibaba.fastjson.JSON;
import com.xsu.web.common.conf.ErrorCodeConf;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;

public class WebServerCilent {
    private static final Charset UTF8_CHARSET = Charset.forName("utf-8");
    private int connectTimeout=10000;
    private int readTimeout=10000;

    @Value("${server.url}")
    private String bashUrl;

    public <T extends BaseResponse> T execute (BaseRequest<T> request) throws IOException{
        return execute(request,readTimeout);
    }

    public <T extends BaseResponse> T execute (BaseRequest<T> request,int timeout) throws IOException{
        if(timeout<0)timeout=0;

        //实例化response
        T response=newResponseInstance(request);

        //检验request
        CheckResult checkResult=request.check();
        if(!ErrorCodeConf.FLAG_SUCCESS.equals(checkResult.getFlag())){
            response.setFlag(ErrorCodeConf.FLAG_FAILURE);
            response.setCode(ErrorCodeConf.FAILURE);
            response.setMsg(checkResult.getMsg());
        }

        String methodName=request.getMethodName();
        methodName=methodName.replaceAll("\\.","/");
        Map<String,Object> paramsMap=request.getParamsMap();
        String paramsJsonStr= MapUtils.isNotEmpty(paramsMap)? JSON.toJSONString(paramsMap):"";

        StringBuilder urlStr=new StringBuilder(bashUrl);
        if(!bashUrl.endsWith("/")){
            urlStr.append("/");
        }

        urlStr.append("v").append(methodName);
        URLConnection conn=null;
        try {
            URL url=new URL(urlStr.toString());
            conn=url.openConnection();
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(timeout);
        } catch (IOException e) {
            e.printStackTrace();
        }

        conn.setRequestProperty("Content-Type", "application/octet-stream");
        conn.setDoInput(true);

        if(!StringUtils.isEmpty(paramsJsonStr)){
            OutputStream out = null;
            try {
                conn.setDoInput(true);

                out = conn.getOutputStream();
                out.write(paramsJsonStr.getBytes("utf-8"));
                out.flush();
            }catch (IOException e){
                throw e;
            }finally {
                IOUtils.closeQuietly(out);
            }
        }

        InputStream in=null;
        try{
            in=conn.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(in,UTF8_CHARSET));
            String responseStr=IOUtils.toString(reader);
            response=(T)JSON.toJavaObject(JSON.parseObject(responseStr),response.getClass());
            return response;
        }catch (IOException e){
            throw e;
        }finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 通过request获取对应的response实例
     * @param request
     * @param <T>
     * @return
     * @throws IOException
     */
    private <T extends BaseResponse> T newResponseInstance(BaseRequest<T> request)throws IOException{
        Class<T> clazz=getResponseType(request);
        try{
            return clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过request获取对应的response的clazz
     * @param request
     * @param <T>
     * @return
     * @throws IOException
     */
    private <T extends BaseResponse> Class<T> getResponseType(BaseRequest<T> request)throws IOException{
        Class<?> requestClass=request.getClass();
        //当前对象的直接超类的type
        Type superType=requestClass.getGenericSuperclass();
        if(!(superType instanceof ParameterizedType)){
            throw new IOException("getResponseType error 1");
        }
        //参数化类型
        ParameterizedType type=(ParameterizedType)superType;
        //返回表示此类型实际类型参数的type对象数组
        Type[] responseTypes=type.getActualTypeArguments();
        if(responseTypes.length!=1){
            throw  new IOException("getResposeType error 2");
        }
        Class<T> clazz=(Class<T>)responseTypes[0];
        return clazz;
    }
}

