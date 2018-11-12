package com.baizhi.service;


import com.baizhi.dao.CourseDAO;
import com.baizhi.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDAO courseDAO;
    @Override
    public void add(Course course) {
        course.setId(UUID.randomUUID().toString());
        courseDAO.save(course);
    }

    @Override
    public void update(Course course) {
        courseDAO.update(course);
}

    @Override
    public void delete(String id) {
        courseDAO.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Course> findByPage(Integer page, Integer rows) {
        int start=(page-1)*rows;

        return courseDAO.findByPage(start,rows);
    }

    @Override
    public Course queryOne(String id) {

        return courseDAO.queryOne(id);
    }

    @Override
    public Long findTotals() {
        return courseDAO.findTotals();
    }
}
