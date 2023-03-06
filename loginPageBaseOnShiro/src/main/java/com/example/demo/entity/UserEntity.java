package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class UserEntity {

    private Integer id;
    private String username;
    private String password;
    @JsonProperty("role_id")
    private Integer roleId;



}
