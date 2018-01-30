//package com.xsu.web.api.controller.user;
//
//import com.xsu.web.common.client.WebServerCilent;
//import com.xsu.web.common.utils.RequestUtils;
//import com.xsu.web.server.sdk.user.req.UserLoginRequest;
//import com.xsu.web.server.sdk.user.resp.UserLoginResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@RequestMapping("/user")
//@RestController
//public class UserController {
//    @Autowired
//    private WebServerCilent serverClient;
//    @RequestMapping("/login")
//    public void login(HttpServletRequest request,HttpServletResponse response) throws Exception {
//        UserLoginRequest req=RequestUtils.toObject(request,UserLoginRequest.class);
//
//        UserLoginResponse resp=serverClient.execute(req);
//
//        if(resp==null||!resp.isSuccess()){
//            System.out.println("login failed");
//        }
//        System.out.println("login success:"+resp.getUser());
//    }
//}
