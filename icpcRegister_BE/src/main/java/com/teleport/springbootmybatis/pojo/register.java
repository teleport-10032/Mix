package com.teleport.springbootmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class register {
    int id;
    String theClass;
    String name;
    String studentId;
    String sex;
    String email;
    String joinType;
    int isPay;
}
