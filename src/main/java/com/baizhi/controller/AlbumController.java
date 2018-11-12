package com.baizhi.controller;


import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
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
@RequestMapping("/Album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;


    @RequestMapping("/findAll")
    public  @ResponseBody
   List<Album> findAll(){
        List<Album> all = albumService.findAll();
        return all;
    }

    @RequestMapping("/save")
    public @ResponseBody
    Map<String,Object> save(Album album, HttpServletRequest request, MultipartFile file){
        Map<String,Object> results = new HashMap<String,Object>();
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/img/album");
            String filename = file.getOriginalFilename();
            file.transferTo(new File(realPath,filename));
            album.setCoverImg(filename);
            albumService.add(album);
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




}
