package com.baizhi.Test;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserTest extends BasicTest {
    @Autowired
    private UserService UserService;


    @Test
    public void test1(){
        User user=new User();
        user.setUsername("陈");
        user.setCity("123");
        user.setGender("123");
        user.setHeadPic("123");
        user.setNickName("渣渣辉");
        user.setProvince("123");
        user.setSalt("");
        user.setSign("123");
        user.setStatus("123");
        user.setPassword("123");
        user.setPhoneNum(54321);
        user.setUserdate(new Date());
        UserService.add(user);
        System.out.println(user);
    }


   /* @Test
    public void test2(){
        UserService.delete("e0fcc576-3716-4858-902f-b6d61f23de5a");
    }


   *//* @Test
    public void test3(){
        List<User> users = UserService.findByPage(1, 2);
        for (User u : users) {
            System.out.println(u);
        }
    }*//*
     @Test
    public void test4(){
         User user = UserService.queryOne("c4bbddc1-923d-43f0-97ea-828e28fa9253");
        System.out.println(user);
    }

    */  @Test
    public void test5(){
        User user =new  User();
        user.setUsername("陈");
        user.setCity("123");
        user.setGender("123");
        user.setHeadPic("123");
        user.setNickName("渣渣辉,渣渣辉");
        user.setProvince("123");
        user.setSalt("123");
        user.setSign("123");
        user.setStatus("禁用");
        user.setPassword("123");
        user.setUserdate(new Date());
        user.setPhoneNum(54321);
        UserService.update(user);
        System.out.println(user);
    }
  @Test
    public void test6(){
         User user=new User();
         user.setId(54321);
         user.setPassword("123");
         user.setPhoneNum(54321);
        //User login = UserService.login(user);
    }

    @Test
    public void test7(){
        User user1 = UserService.queryOne(54321);
        UserService.updateStatus(user1);
    }

}
