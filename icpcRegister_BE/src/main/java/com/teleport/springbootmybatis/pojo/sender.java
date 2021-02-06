package com.teleport.springbootmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class sender {
    String email;
    String  verCode;
    int times;
}
