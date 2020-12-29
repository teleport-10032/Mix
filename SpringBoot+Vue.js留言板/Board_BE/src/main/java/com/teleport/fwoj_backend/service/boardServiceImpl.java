package com.teleport.fwoj_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.mapper.boardMapper;
import com.teleport.fwoj_backend.pojo.board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
public class boardServiceImpl implements boardService{

    @Autowired
    private  boardMapper boardMapperObject;
    @Override
    public String getBoardList() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<board> list = boardMapperObject.getBoardList();
        HashMap s = new HashMap();
        s.put("data", list);
        return mapper.writeValueAsString(s);
    }

    @Override
    public String boardSubmit(String title, String content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(boardMapperObject.boardSubmit(title,content) == 1)
            s.put("result","1");
        else
            s.put("result","0");
        return mapper.writeValueAsString(s);
    }
}
