package com.baizhi.controller;


import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
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
@RequestMapping("/Banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;


    @RequestMapping("/findAll")
    public  @ResponseBody Map<String,Object> findAll(Integer page,Integer rows){
        page= page==null?1:page;
        rows= rows==null?2:rows;
        Map<String,Object> result = new HashMap<String,Object>();
        List<Banner> lists = bannerService.findByPage(page, rows);
        result.put("rows",lists);
        result.put("total",bannerService.findTotals());
        return result;
    }

    @RequestMapping("/save")
    public @ResponseBody
    Map<String,Object> save(Banner banner, HttpServletRequest request, MultipartFile file){
        Map<String,Object> results = new HashMap<String,Object>();
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/img/banner");
            String filename = file.getOriginalFilename();
            file.transferTo(new File(realPath,filename));

            banner.setImgPath(filename);
          /*  String s = FileUtil.saveFile(file, "/img/banner", request);*/
            banner.setImgPath(filename);
            bannerService.add(banner);
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
            bannerService.delete(id);
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
    Banner queryOne(String id){




        return  bannerService.queryOne(id);
    }

    @RequestMapping("/update")
    public @ResponseBody
    Map<String,Object> updata(Banner banner,HttpServletRequest request, MultipartFile filea){
        System.out.println(banner);
        Map<String,Object> results = new HashMap<String,Object>();
        try {
            Banner banner1 = bannerService.queryOne(banner.getId());
        /*    System.out.println("轮播图fshdajkk"+banner1);*/
            String realPath1 = request.getSession().getServletContext().getRealPath(banner1.getImgPath());
         /*   System.out.println("原来的路径"+realPath1);*/
            File file1 = new File(realPath1);
            file1.delete();
        /*    System.out.println(delete);*/
            String realPath = request.getSession().getServletContext().getRealPath("/img/banner");
            String filename = filea.getOriginalFilename();
           /* System.out.println("新的路径"+realPath);
            System.out.println(filename);*/
            filea.transferTo(new File(realPath,filename));
            banner.setImgPath(filename);
        /*    String file = FileUtil.saveFile(filea, "/img/banner", request);
            System.out.println("file"+file);*/
            banner.setImgPath(filename);
            bannerService.update(banner);
            results.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }




}
