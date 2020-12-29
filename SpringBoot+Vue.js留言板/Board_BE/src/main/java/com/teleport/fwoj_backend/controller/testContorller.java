package com.teleport.fwoj_backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teleport.fwoj_backend.service.boardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class testContorller {

    @Autowired
    private boardService boardServiceObject;

    @RequestMapping(value = "/boardSubmit", method = {RequestMethod.POST})
    @CrossOrigin
    public String boardSubmit(@RequestParam("title") String title,
                              @RequestParam("content") String content) throws JsonProcessingException {
        return boardServiceObject.boardSubmit(title,content);
    }


    @RequestMapping(value = "/getBoardList", method = {RequestMethod.GET})
    @CrossOrigin
    public String getBoardList() throws JsonProcessingException {
        return boardServiceObject.getBoardList();
    }

}
