package com.teleport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AjaxController {

    @RequestMapping("/t1")
    public String qwq() {
        return "ajaxFrame";
    }

    @RequestMapping("/t2")
    public String qwq2(){return "test2";}
    @RequestMapping("/t3")
    public String qwq3(){return "login";}
    @RequestMapping("/t4")
    public String qwq4(){return "baiduDemo";}
}
