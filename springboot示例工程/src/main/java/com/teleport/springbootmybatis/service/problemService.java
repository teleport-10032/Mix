package com.teleport.springbootmybatis.service;

import com.teleport.springbootmybatis.pojo.problem;

import java.util.List;

public interface problemService {
    // 获取所有部门信息
    List<problem> getProblems();
    // 通过id获得部门
    problem getProblem(Integer id);
}
