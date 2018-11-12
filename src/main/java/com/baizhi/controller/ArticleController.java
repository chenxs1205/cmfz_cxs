package com.baizhi.controller;


import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
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
@RequestMapping("/Article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @RequestMapping("/findAll")
    public  @ResponseBody Map<String,Object> findAll(Integer page,Integer rows){
        page= page==null?1:page;
        rows= rows==null?2:rows;
        Map<String,Object> result = new HashMap<String,Object>();
        List<Article> lists = articleService.findByPage(page, rows);
        result.put("rows",lists);
        result.put("total",articleService.findTotals());
        return result;
    }

    @RequestMapping("/save")
    public @ResponseBody
    Map<String,Object> save(Article article, HttpServletRequest request, MultipartFile file){
        Map<String,Object> results = new HashMap<String,Object>();
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/img/article");
            String filename = file.getOriginalFilename();
            System.out.println(realPath);
            System.out.println(filename);
            file.transferTo(new File(realPath,filename));
            article.setImgPath(filename);
            articleService.add(article);
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
            articleService.delete(id);
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
    Article queryOne(String id){

        return  articleService.queryOne(id);
    }

    @RequestMapping("/update")
    public @ResponseBody
    Map<String,Object> updata(Article article,HttpServletRequest request, MultipartFile file){
        System.out.println(article);
        Map<String,Object> results = new HashMap<String,Object>();
        try {
            Article article1 = articleService.queryOne(article.getId());
            String realPath1 = request.getSession().getServletContext().getRealPath(article.getImgPath());
            File file1 = new File(realPath1);
          /*  System.out.println("原来的路径"+realPath1);*/
            boolean delete = file1.delete();
            System.out.println(delete);
            String realPath = request.getSession().getServletContext().getRealPath("/img/article");
            String filename = file.getOriginalFilename();
          /*  System.out.println("新的路径"+realPath);
            System.out.println(filename);*/
            file.transferTo(new File(realPath,filename));
            article.setImgPath(filename);
            articleService.update(article);
            results.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }




}
