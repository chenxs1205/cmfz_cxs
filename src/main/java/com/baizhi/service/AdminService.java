package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService {
    Admin login(Admin admin);

    void updatePwd(Admin admin);
}
