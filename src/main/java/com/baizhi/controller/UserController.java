package com.baizhi.controller;


import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/findAll")
    public  @ResponseBody Map<String,Object> findAll(Integer page,Integer rows){
        page= page==null?1:page;
        rows= rows==null?2:rows;
        Map<String,Object> result = new HashMap<String,Object>();
        List<User> lists = userService.findByPage(page, rows);
        result.put("rows",lists);
        result.put("total",userService.findTotals());
        return result;
    }
    @RequestMapping("/save")
    public @ResponseBody
    Map<String,Object> save(User user, HttpServletRequest request, MultipartFile file){
        Map<String,Object> results = new HashMap<String,Object>();
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/img/user");
            String filename = file.getOriginalFilename();
            file.transferTo(new File(realPath,filename));
            user.setHeadPic(filename);
            userService.add(user);
            results.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }

    @RequestMapping("/delete")
    public @ResponseBody
    Map<String,Object> delete(int id){
        System.out.println(id);
        Map<String,Object> results = new HashMap<String,Object>();
        try {
            userService.delete(id);
            results.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }


    @RequestMapping("/queryOne")
    public @ResponseBody
    User queryOne(int id){
        return  userService.queryOne(id);
    }

    @RequestMapping("/update")
    public @ResponseBody
    Map<String,Object> updata(User user,HttpServletRequest request, MultipartFile file){
        Map<String,Object> results = new HashMap<String,Object>();
        try {
           /* User user1 = userService.queryOne(user.getId());
            String realPath1 = request.getSession().getServletContext().getRealPath(user1.getHeadPic());
            File file1 = new File(realPath1);
            boolean delete = file1.delete();
            System.out.println(delete);*/
            String realPath = request.getSession().getServletContext().getRealPath("/img/user");
            String filename = file.getOriginalFilename();
            file.transferTo(new File(realPath,filename));
            user.setHeadPic(filename);
            userService.update(user);
            results.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }

    @RequestMapping("/login")
    public @ResponseBody String
     login(User  user) {
      return userService.login(user);

    }

    @RequestMapping("/updateStatus")
    public @ResponseBody
    Map<String,Object> UserUpdateStatus(User user){
        Map<String,Object> results = new HashMap<String,Object>();
        try {
            User user1 = userService.queryOne(user.getId());
            userService.updateStatus(user1);
            results.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }
}
