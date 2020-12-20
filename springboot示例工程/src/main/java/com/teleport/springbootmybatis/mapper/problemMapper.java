package com.teleport.springbootmybatis.mapper;

import com.teleport.springbootmybatis.pojo.problem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface problemMapper {

    // 获取所有部门信息
    List<problem> getProblems();

    // 通过id获得部门
    problem getProblem(Integer id);

}
