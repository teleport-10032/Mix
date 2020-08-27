package com.teleport.controller;

import com.teleport.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


//对象传参
@Controller
public class UserController {
    @RequestMapping(value = "/user")
    public String user(User user){
        System.out.println(user.getId());
        System.out.println(user.getSex());
        System.out.println(user.getName());
        return "hello";
    }
}
