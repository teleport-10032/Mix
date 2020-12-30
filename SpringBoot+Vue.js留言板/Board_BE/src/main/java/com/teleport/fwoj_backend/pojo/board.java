package com.teleport.fwoj_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class board {
    private int id;
    private String title;
    private String content;
    private String account;
    private Date insertDate;
    private String date;
}
