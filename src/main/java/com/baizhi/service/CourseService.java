package com.baizhi.service;

import com.baizhi.entity.Course;

import java.util.List;

public interface CourseService {
    void add(Course course);

    void update(Course course);

    void delete(String id);

    Long findTotals();

    List<Course> findByPage(Integer page, Integer rows);

    Course queryOne(String id);

}
