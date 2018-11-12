package com.baizhi.Test;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminTest extends BasicTest{
    @Autowired
    private AdminService adminService;

    @Test
    public void test1(){
        Admin admin=new Admin();
        admin.setName("陈");
        admin.setPassword("123");
        adminService.login(admin);
    }

    @Test
    public void test2(){
        Admin admin=new Admin();
        admin.setPassword("123");
        admin.setId("1");
        adminService.updatePwd(admin);
    }

}
