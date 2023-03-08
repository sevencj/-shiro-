package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    @Select("select role_name from role left join role_user on role.id=role_user.rid left join user on user.id=role_user.uid where username=#{principal}")
    List<String> getUserRoleInfo(@Param("principal")String principal);


}
