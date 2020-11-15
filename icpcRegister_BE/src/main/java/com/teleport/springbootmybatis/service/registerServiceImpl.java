package com.teleport.springbootmybatis.service;

import com.teleport.springbootmybatis.mapper.registerMapper;
import com.teleport.springbootmybatis.pojo.register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class registerServiceImpl implements registerService {

    @Autowired
    registerMapper registerMapperObject;

    @Override
    public boolean isEmailExits(String email) {
        if(registerMapperObject.isEmailExits(email) == 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean register(String theClass, String name, String studentId, String sex, String email, String joinType,int isPay) {
        if(registerMapperObject.register(theClass,name,studentId,sex,email,joinType,isPay) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean changeIsPayByEmail(String email) {
        if(registerMapperObject.getIsPayByEmail(email) == 1)
            if(registerMapperObject.setIsPayByEmail(email,0) == 1)
                return true;
            else
                return false;
        else
            if(registerMapperObject.setIsPayByEmail(email,1) == 1)
                return true;
            else
                return false;
    }

    @Override
    public int getSum() {
        return registerMapperObject.getSum();
    }

    @Override
    public List<register> getList(int page, int pre, String key) {
        int start = pre * (page - 1);
        int num = pre;
        return registerMapperObject.getList(start,num,key);
    }

    @Override
    public int setIsPayByName(String name, int value) {
        return registerMapperObject.setIsPayByName(name,value);
    }
}
