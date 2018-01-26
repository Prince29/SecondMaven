package com.xsu.web.api;

import com.xsu.web.server.entity.User;

public class UserController {

    public static void main(String[] args) {
        User user=new User();
        user.setAge(18);
        user.setName("xsu");
        user.setSex("M");
        System.out.println(user);
    }

}