package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Admin")

public class AdminController {

    @Autowired
    private AdminService adminService;


    @RequestMapping("login")
    public String login(Admin admin, String enCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(admin);
        Admin admins = adminService.login(admin);
        session.setAttribute("admin", admins);
        Object code = session.getAttribute("validationCode");
       if(code.equals(enCode)){
           if (admins == null) {
               System.out.println("登陆失败");
               return "redirect:/login.jsp";
           }
           System.out.println("登陆成功");
           return "redirect:/main/main.jsp";

       }else{
           return "redirect:/login.jsp";
       }

    }
    @RequestMapping("LoggedOut")
    public String LoggedOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("admin",null);
        return "redirect:/login.jsp";
    }


    @RequestMapping("/updatePwd")
    public @ResponseBody
    Map<String,Object> updatePwd(Admin admin, HttpServletRequest request){
        System.out.println(admin);
        Map<String,Object> results = new HashMap<String,Object>();
        try {
            adminService.updatePwd(admin);
            LoggedOut(request);
            results.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }

}
