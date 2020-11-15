package com.teleport.springbootmybatis.service;

import com.teleport.springbootmybatis.mapper.testMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class sendServiceImpl implements sendService{

    @Autowired
    testMapper testMapperObject;

    @Override
    public boolean isEmailExits(String email) {
        if(testMapperObject.isEmailExits(email) == 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean add(String email, String verCode, int canSend) {
        int r = 0;
        try
        {
            r = testMapperObject.add(email,verCode,canSend);
        }
        catch (Exception e)
        {
            return false;
        }
        if(r == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean update(String email, String verCode, int canSend) {
        if(testMapperObject.update(email,verCode,canSend) == 1)
            return true;
        else
            return false;
    }

    @Override
    public int getTimesByEmail(String email) {
        return testMapperObject.getTimesByEmail(email);
    }

    @Override
    public boolean setTimesByEmail(String email, int canSend) {
        if(testMapperObject.setTimesByEmail(email,canSend) == 1)
            return true;
        else
            return false;
    }

    @Override
    public String getVerCodeByEmail(String email) {
        return testMapperObject.getVerCodeByEmail(email);
    }


}
