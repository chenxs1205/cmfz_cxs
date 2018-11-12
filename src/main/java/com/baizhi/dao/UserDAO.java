package com.baizhi.dao;

import com.baizhi.entity.User;

public interface UserDAO extends BaseDAO<User>{
    User login(User user);

    void delete(int id);

    //查一果
    User queryOne(int id);

    void updateStatus(User user);
}
