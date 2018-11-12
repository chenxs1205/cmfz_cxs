package com.baizhi.controller;


import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
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
@RequestMapping("/Guru")
public class GuruController {

    @Autowired
    private GuruService guruService;


    @RequestMapping("/findAll")
    public  @ResponseBody Map<String,Object> findAll(Integer page,Integer rows){
        page= page==null?1:page;
        rows= rows==null?2:rows;
        Map<String,Object> result = new HashMap<String,Object>();
        List<Guru> lists = guruService.findByPage(page, rows);
        result.put("rows",lists);
        result.put("total",guruService.findTotals());
        return result;
    }

    @RequestMapping("/save")
    public @ResponseBody
    Map<String,Object> save(Guru guru, HttpServletRequest request, MultipartFile file){
        Map<String,Object> results = new HashMap<String,Object>();
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/img/guru");
            String filename = file.getOriginalFilename();
          /*  System.out.println(realPath);
            System.out.println(filename);*/
            file.transferTo(new File(realPath,filename));
            guru.setHeadPic(filename);
            System.out.println(guru);
            guruService.add(guru);
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
    Map<String,Object> delete(String id){
        System.out.println(id);
        Map<String,Object> results = new HashMap<String,Object>();
        try {
            guruService.delete(id);
            results.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }


    @RequestMapping("/FindById")
    public @ResponseBody
    Guru FindById(String id){

          return   guruService.FindById(id);

    }
    @RequestMapping("/FindAllName")
    public @ResponseBody
    List<Guru> FindAllName(){

        return   guruService.findAllName();

    }


}
