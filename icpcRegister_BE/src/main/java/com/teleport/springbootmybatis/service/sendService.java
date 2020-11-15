package com.teleport.springbootmybatis.service;

public interface sendService {
    //查找email是否存在
    boolean isEmailExits(String email);
    //插入一条 email verCode canSend
    boolean add(String email,String verCode,int times);
    //根据email更新verCode canSend
    boolean update(String email,String verCode,int times);
    //根据email查询times
    int getTimesByEmail(String email);
    //根据email更新times
    boolean setTimesByEmail(String email,int canSend);
    //根据email查询verCode
    String getVerCodeByEmail(String email);
}
