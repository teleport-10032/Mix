package com.test.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//若是@RestController则不会经过试图解析器，直接返回String
@Controller
@RequestMapping("/hello")
public class HelloController {

    //真实访问地址 : 项目名/HelloController/hello
    @RequestMapping("/hello")
    public String sayHello(Model model){
        //向模型中添加属性msg与值，可以在JSP页面中取出并渲染
        model.addAttribute("msg","hello,SpringMVC!");
        //web-inf/jsp/hello.jsp
        return "hello";
    }


    @RequestMapping("/hello2")
    public String sayHello2(Model model)
    {
        //向模型中添加属性msg与值，可以在JSP页面中取出并渲染
        model.addAttribute("msg","hello,SpringMVC_test2");
        //web-inf/jsp/hello.jsp
        return "hello";
    }


    @RequestMapping("/hello3")
    public String sayHello3(Model model)
    {
        //向模型中添加属性msg与值，可以在JSP页面中取出并渲染
        model.addAttribute("msg","hello,SpringMVC_test3");
        //web-inf/jsp/hello.jsp
        return "hello";
    }


}