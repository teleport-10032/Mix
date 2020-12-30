package com.teleport.fwoj_backend.service;


import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.ParseException;

public interface boardService {
    //获取所有title content
    String getBoardList() throws JsonProcessingException, ParseException;
    //提交title content
    String boardSubmit(String title,String content,String account) throws JsonProcessingException;
}
