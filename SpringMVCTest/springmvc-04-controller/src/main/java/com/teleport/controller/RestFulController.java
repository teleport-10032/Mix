package com.teleport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLDecoder;

@Controller
public class RestFulController {


    //映射访问路径,并限定为POST/GET请求
    //@RequestMapping("/add/{a}/{b}",method = {RequestMethod.POST})
    //@GetMapping("/add/{a},{b}")
    //@RequestMapping("/add/{a}/{b}",method = {RequestMethod.GET})
    //@PostMapping("/add/{a},{b}")

    //映射访问路径,并指定变量
    @RequestMapping(value = "/add/{a}/{b}")
    public String test1(@PathVariable int a , @PathVariable int b , Model model)
    {
        int res = a + b;
        model.addAttribute("msg",a + " + " + b + " = " + (a+b));
        return "test";
    }


}
