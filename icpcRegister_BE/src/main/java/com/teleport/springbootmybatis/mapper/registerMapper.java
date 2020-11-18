package com.teleport.springbootmybatis.mapper;

import com.teleport.springbootmybatis.pojo.register;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface registerMapper {

    //查询email是否存在
    int isEmailExits(String email);
    //注册
    int register(String theClass, String name, String studentId, String sex, String email, String joinType,int isPay);
    //根据name查询isPay
    int getIsPayByEmail(String email);
    //根据name设置isPay
    int setIsPayByEmail(String email,int isPay);
    //获取总数
    int getSum(String key);
    //查询列表(页数,每页几条),返回List
    List<register> getList(int start, int num,String key);

    int setIsPayByName(String name,int value);

}
