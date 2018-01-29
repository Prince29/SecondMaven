package com.xsu.web.common.client;


import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class WebServerCilent {
    private int connectTimeout=10000;

    @Value("${server.url}")
    private String urlStr;


    public <T extends BaseResponse> T execute (BaseRequest<T> request,int timeout) throws IOException{
        if(timeout<0)timeout=0;
        T response=newResponseInstance(request);

        URLConnection conn=null;
        try {
            URL url=new URL(urlStr);
            conn=url.openConnection();
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(timeout);
            conn.setRequestProperty("Content-Type", "application/octet-stream");
        } catch (IOException e) {
            e.printStackTrace();
        }



        conn.connect();

        Map<String,List<String>> headers=conn.getHeaderFields();
        System.out.println(headers);

        BufferedReader reader=null;
        StringBuffer resultBuffer=new StringBuffer();
        String tempLine=null;
        reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while((tempLine=reader.readLine())!=null){
            resultBuffer.append(tempLine);
        }

        reader.close();
        System.out.println(resultBuffer.toString());
        //reurn resultBuffer.toString();
        return null;
    }
    private <T extends BaseResponse> T newResponseInstance(BaseRequest request)throws IOException{
        Class<T> clazz=getResponseType(request);
        try{
            return clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private <T extends BaseResponse> Class<T> getResponseType(BaseRequest request)throws IOException{
        Class<?> requestClass=request.getClass();
        Type superType=requestClass.getGenericSuperclass();
        if(!(superType instanceof ParameterizedType)){
            throw new IOException("getResponseType error 1");
        }
        ParameterizedType type=(ParameterizedType)superType;
        Type[] responseTypes=type.getActualTypeArguments();
        if(responseTypes.length!=1){
            throw  new IOException("getResposeType error 2");
        }
        Class<T> clazz=(Class<T>)responseTypes[0];
        return clazz;
    }
}

