package com.baizhi.controller;


import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/save")
    public @ResponseBody
    Map<String, Object> save(Chapter chapter, HttpServletRequest request, MultipartFile file) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            String realPath = FileUtil.saveFile(file,"/audio/chapter/",request);
            System.out.println(realPath);
            String length=FileUtil.AudioLength(realPath,request);

            System.out.println(length);

            String size= String.valueOf(FileUtil.AudioSize(realPath,request));
            System.out.println(size);
            chapter.setSize(size+"M");
            chapter.setDownPath(realPath);
            chapter.setDuration(length);
            chapterService.add(chapter);
            results.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success", false);
            results.put("message", e.getMessage());
        }
        return results;
    }

    @RequestMapping("download")
    @ResponseBody

    public void download(String downPath,String openStyle,HttpServletRequest request,HttpServletResponse response) throws Exception{
        String realPath=request.getSession().getServletContext().getRealPath("/");
        System.out.println(realPath);

        FileInputStream is=new FileInputStream(new File(realPath,downPath));

        response.setHeader("content-disposition",openStyle+";fileName="+ URLEncoder.encode(downPath,"UTF-8"));

        ServletOutputStream os=response.getOutputStream();
        IOUtils.copy(is,os);

        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);


    }
    /*@RequestMapping("/delete")
    public @ResponseBody
    Map<String,Object> delete(String id){
        System.out.println(id);
        Map<String,Object> results = new HashMap<String,Object>();
        try {
            albumService.delete(id);
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
    Album queryOne(String id){


        return  albumService.queryOne(id);
    }

    @RequestMapping("/update")
    public @ResponseBody
    Map<String,Object> updata(Album album,HttpServletRequest request, MultipartFile file){
        System.out.println(album);
        Map<String,Object> results = new HashMap<String,Object>();
        try {

            albumService.update(album);
            results.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }

*/


}
