package com.teleport.fwoj_backend.service;


import com.fasterxml.jackson.core.JsonProcessingException;

public interface boardService {
    //获取所有title content
    String getBoardList() throws JsonProcessingException;
    //提交title content
    String boardSubmit(String title,String content) throws JsonProcessingException;
}
