package com.teleport.springbootmybatis.service;
import com.teleport.springbootmybatis.pojo.register;
import java.util.List;

public interface registerService {
    //查询email是否存在
    boolean isEmailExits(String email);
    //注册 theClass,name,studentId,sex,email,joinType
    boolean register(String theClass, String name, String studentId, String sex, String email, String joinType,int isPay);
    //通过name改变isPay状态
    boolean changeIsPayByEmail(String email);
    //获取总数
    int getSum(String key);
    //查询列表(页数,每页几条),返回List
    List<register> getList(int page,int pre,String key);

    int setIsPayByName(String name,int value);

}
