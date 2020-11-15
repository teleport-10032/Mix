package com.teleport.springbootmybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface testMapper {
//    int getEmailSumByEmail(String email);

    //查找email是否存在
    int isEmailExits(String email);
    //插入一条 email verCode canSend
    int add(String email,String verCode,int times);
    //根据email更新verCode canSend
    int update(String email,String verCode,int times);
    //根据email查询times
    int getTimesByEmail(String email);
    //根据email更新times
    int setTimesByEmail(String email,int canSend);
    //根据email查询verCode
    String getVerCodeByEmail(String email);

}
