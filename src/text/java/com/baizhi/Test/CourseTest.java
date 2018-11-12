package com.baizhi.Test;

import com.baizhi.entity.Course;
import com.baizhi.service.CourseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class CourseTest extends BasicTest {
    @Autowired
    private CourseService courseService;


    @Test
    public void test1(){
        Course course=new Course();



        courseService.add(course);
    }


    @Test
    public void test2(){
        courseService.delete("30d28335-da53-41c6-a6dd-a0241f0dfb30");
    }


    @Test
    public void test3(){
        List<Course> emps = courseService.findByPage(1, 2);
        for (Course emp : emps) {
            System.out.println(emp);
        }
    }
    @Test
    public void test4(){
        Course course = courseService.queryOne("2c71cbd6-58fc-43a3-8e81-f03eec0d83c1");
        System.out.println(course);
    }

    @Test
    public void test5(){
        Course course =new  Course();
        course.setTitle("1231231");
        course.setMarking("123");
        course.setCreatTime(new Date());
        course.setId("2c71cbd6-58fc-43a3-8e81-f03eec0d83c1");
        courseService.update(course);
        System.out.println(course);
    }
}
