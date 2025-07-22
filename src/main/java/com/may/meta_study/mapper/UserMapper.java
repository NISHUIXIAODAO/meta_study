package com.may.meta_study.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select user_name from users where user_id = #{userId}")
    String getUserName(@Param("userId") Long userId);
}
