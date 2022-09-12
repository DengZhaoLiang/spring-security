package com.liang.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.springsecurity.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

}