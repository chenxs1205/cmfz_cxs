package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Override
    public void add(User user) {
        user.setId(user.getPhoneNum());
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setPassword(DigestUtils.md5Hex((user.getPassword()+user.getSalt())));
        userDAO.save(user);
    }
    @Override
    public void update(User user) {
        user.setId(user.getPhoneNum());
        userDAO.update(user);
}

    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    public User queryOne(int id) {

        return userDAO.queryOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findByPage(Integer page, Integer rows) {
        int start=(page-1)*rows;

        return userDAO.findByPage(start,rows);
    }

    @Override
    public String  login(User user) {
        String password = user.getPassword();
        User user1 = userDAO.queryOne(user.getPhoneNum());
        System.out.println(user1);
        System.out.println(password);

        String password1 = user1.getPassword();
        System.out.println(password1);

        String salt = user1.getSalt();
        System.out.println(salt);

        String s = DigestUtils.md5Hex(password + salt);
        System.out.println(s);
        User login = userDAO.login(user);
        if(  password1.equals(s)){
            System.out.println("登陆成功");
            return "登陆成功";
        }
        System.out.println("登陆失败");
          return  "登陆失败";
    }

    @Override
    public void updateStatus(User user) {
        if(user.getStatus().equals("激活")){
            user.setStatus("禁用");
        }else{
            user.setStatus("激活");
        }
        userDAO.updateStatus(user);
    }

    @Override
    public Long findTotals() {
        return userDAO.findTotals();
    }


}
