package com.atguigu.gmall.manager.mapper;

import com.atguigu.gmall.manager.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User> {

    public User getUserHaha(User user);
}
