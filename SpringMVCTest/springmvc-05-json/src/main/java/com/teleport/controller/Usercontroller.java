package com.teleport.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.teleport.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class Usercontroller {


    @RequestMapping(value = "/j1")
    //不会走试图解析器，直接返回字符串
//    @ResponseBody
    public String json1() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        User user = new User(15,"111","男");
        String str = mapper.writeValueAsString(user);
        return str;
    }


    //返回集合
    //@ResponseBody
    @RequestMapping(value = "/j2")
    public String json2() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        User user = new User(15,"1","男");
        User user2 = new User(15,"2","男");
        User user3 = new User(15,"3","男");
        User user4 = new User(15,"4","男");

        List<User> userlist =  new ArrayList<User>();

        userlist.add(user);
        userlist.add(user2);
        userlist.add(user3);
        userlist.add(user4);

        String str = mapper.writeValueAsString(userlist);
        return str;
    }


    //@ResponseBody
    @RequestMapping(value = "/j3")
    public String json3() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        //不使用时间戳的方式
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //自定义日期格式对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //指定日期格式
        mapper.setDateFormat(sdf);

        Date date = new Date();

        String str = mapper.writeValueAsString(date);
        return str;
    }


}
