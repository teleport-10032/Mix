package com.teleport.springbootmybatis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.springbootmybatis.pojo.problem;
import com.teleport.springbootmybatis.service.problemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class problemController {

    @Autowired
    private problemService problemServiceObject;

    @RequestMapping("/hello")
    public String hello()
    {
        return "HelloWorld3";
    }

    // 查询全部
    @RequestMapping("/getProblems")
    public String getProblems() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<problem> list = problemServiceObject.getProblems();
        HashMap s = new HashMap();
        s.put("data",list);
        s.put("status",200);
        return mapper.writeValueAsString(s);
    }

    // 查询某一个
    @RequestMapping("/getProblem/{id}")
    public String getProblem(@PathVariable("id") Integer id) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        problem problemObject = problemServiceObject.getProblem(id);
        HashMap s = new HashMap();
        s.put("data",problemObject);
        s.put("status",200);
        return mapper.writeValueAsString(s);
    }
}
