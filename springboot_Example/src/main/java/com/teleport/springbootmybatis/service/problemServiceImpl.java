package com.teleport.springbootmybatis.service;

import com.teleport.springbootmybatis.mapper.problemMapper;
import com.teleport.springbootmybatis.pojo.problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class problemServiceImpl implements problemService{
    @Autowired
    private problemMapper problemMapperObject;
    @Override
    public List<problem> getProblems() {
        return problemMapperObject.getProblems();
    }
    @Override
    public problem getProblem(Integer id) {
        return problemMapperObject.getProblem(id);
    }
}
