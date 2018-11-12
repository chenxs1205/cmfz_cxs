package com.baizhi.dao;

import com.baizhi.entity.Admin;

public interface AdminDAO extends BaseDAO<Admin> {
    Admin login(Admin admin);


    void updatePwd(Admin admin);
}
