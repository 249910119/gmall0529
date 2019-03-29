package com.atguigu.gmall.user;

import com.atguigu.gmall.user.User;

public interface UserService {

    public User getUser(String id);

    public void buyMovie(String uId, String mid);
}
