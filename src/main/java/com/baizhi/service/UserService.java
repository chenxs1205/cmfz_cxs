package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void update(User user);

    void delete(int id);

    Long findTotals();

    List<User> findByPage(Integer page, Integer rows);

    User queryOne(int id);

    String login(User user);

    //修改
    void updateStatus(User user);

}
