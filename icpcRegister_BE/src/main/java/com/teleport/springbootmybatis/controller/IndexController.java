package com.teleport.springbootmybatis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.springbootmybatis.pojo.register;
import com.teleport.springbootmybatis.service.SendMail;
import com.teleport.springbootmybatis.service.registerService;
import com.teleport.springbootmybatis.service.sendService;
import com.teleport.springbootmybatis.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
public class IndexController {


    @Autowired
    private sendService sendServiceObject;
    @Autowired
    private registerService registerServiceObject;
    @Autowired
    private testService testServiceObject;
    @Autowired
    private SendMail mailService;

    private static String verCode = "";


    @RequestMapping("/change")
    @CrossOrigin
    public String setIsPayByName(@RequestParam("name") String name,@RequestParam("value") int value,@RequestParam("passwd") String passwd) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(passwd.equals("cam135"))
        {
            s.put("error",0);
            s.put("data",registerServiceObject.setIsPayByName(name,value));
        }
        else
            s.put("error",1);
        return mapper.writeValueAsString(s);
    }



    @RequestMapping("/send")
    @CrossOrigin
    public String verCodeSender(@RequestParam("email") String email) throws JsonProcessingException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        String str = "";
        Random rand = new Random();
        for(int i = 0; i < 6; i ++)
        {
            str += rand.nextInt(10);
        }
        boolean iees = sendServiceObject.isEmailExits(email);
        //先查找sender表中邮箱是否存在。
        if(iees)
        {

            int times = sendServiceObject.getTimesByEmail(email);
            //times>=1则发送邮件，更新verCode，同时times --
            if(times >= 1)
            {
                //send
                //send 失败则error2
                if(sendServiceObject.update(email,str,times-1) &&
                        this.sendSimpleMsg("验证码：" + str +"。您正在报名山东建筑大学第八届ACM-ICPC校赛。",email))
                {
                    s.put("error","0");
                    s.put("times",times-1);
                }
                else
                    s.put("error","2");
            }
            //times<1则返回error1
            else
                s.put("error","1");
        }
        //不存在则发送邮件，插入send一条记录，times=2
        else
        {
            //send 失败则error2
            if(sendServiceObject.add(email,str,2) &&
                    this.sendSimpleMsg("验证码：" + str +"。您正在报名山东建筑大学第八届ACM-ICPC校赛。",email))
            {
                s.put("error","0");
                s.put("times",2);
            }
            else
                s.put("error","2");
        }
        return mapper.writeValueAsString(s);
    }

    //提交报名信息
    //0 正常 1 验证码错误 2 邮箱已参与过报名
    @RequestMapping("/submit")
    @CrossOrigin
    public String submit(@RequestParam("email") String email, @RequestParam("theClass") String theClass,
                         @RequestParam("name") String name, @RequestParam("studentId") String studentId,
                         @RequestParam("sex") String sex,@RequestParam("joinType") String joinType,
                         @RequestParam("verCode") String verCode) throws JsonProcessingException {
//        System.out.println(email);
//        System.out.println(theClass);
//        System.out.println(name);
//        System.out.println(studentId);
//        System.out.println(sex);
//        System.out.println(joinType);
//        System.out.println(verCode);
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();

        int isPay = 0;
        if(joinType.equals("友情参赛"))
            isPay = 2;
        //读取sender表中的email对应的verCode
        String code = sendServiceObject.getVerCodeByEmail(email);
        if(verCode.equals(code))
        {
            //判断邮箱是否注册
            if(!registerServiceObject.isEmailExits(email))
            {
                registerServiceObject.register(theClass,name,studentId,sex,email,joinType,isPay);
                s.put("error","0");
            }
            else
                s.put("error","2");
        }
        //若不对返回error1
        else
        {
            s.put("error","1");
        }
        return mapper.writeValueAsString(s);
    }


    //获取列表
    @RequestMapping(value = "/getList",method = {RequestMethod.GET})
    @CrossOrigin
    public String getProblemList(@RequestParam("page") int page,@RequestParam("pre") int pre,
                                 @RequestParam("key") String key) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<register> list = registerServiceObject.getList(page,pre,key);
        int total  = registerServiceObject.getSum();
        HashMap s = new HashMap();
        s.put("data",list);
        s.put("total",total);
        s.put("error","0");
        return  mapper.writeValueAsString(s);
    }


    public boolean sendSimpleMsg(String msg, String email)
    {
        if(mailService.sendTextMail(email,"请查收您的ICPC校赛报名验证码",msg))
            return true;
        else
            return false;
    }
}
