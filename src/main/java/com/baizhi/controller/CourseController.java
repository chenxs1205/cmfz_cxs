package com.baizhi.controller;


import com.baizhi.entity.Course;
import com.baizhi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/Course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @RequestMapping("/findAll")
    public  @ResponseBody Map<String,Object> findAll(Integer page,Integer rows){
        page= page==null?1:page;
        rows= rows==null?2:rows;
        Map<String,Object> result = new HashMap<String,Object>();
        List<Course> lists = courseService.findByPage(page, rows);
        result.put("rows",lists);
        result.put("total",courseService.findTotals());
        return result;
    }

    @RequestMapping("/save")
    public @ResponseBody
    Map<String,Object> save(Course course){
        Map<String,Object> results = new HashMap<String,Object>();
        try {
            courseService.add(course);
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
    Map<String,Object> delete(String id) {
        Map<String, Object> results = new HashMap<String, Object>();
        Course course = courseService.queryOne(id);
        String marking = course.getMarking();
        if (marking.equals("选修")) {
            results.put("success", false);
            results.put("message","该功课为选修功课，不能删除");
        } else {
            try {
                courseService.delete(id);
                results.put("success", true);
                results.put("message", "删除成功");
            } catch (Exception e) {
                e.printStackTrace();
                results.put("success", false);
                results.put("message", e.getMessage());
            }
            return results;
        }
        return results;
    }


    @RequestMapping("/queryOne")
    public @ResponseBody
    Course queryOne(String id){

        return  courseService.queryOne(id);
    }

    @RequestMapping("/update")
    public @ResponseBody
    Map<String,Object> updata(Course course,HttpServletRequest request, MultipartFile file) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            courseService.update(course);
            results.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success", false);
            results.put("message", e.getMessage());
        }
        return results;
    }

}
