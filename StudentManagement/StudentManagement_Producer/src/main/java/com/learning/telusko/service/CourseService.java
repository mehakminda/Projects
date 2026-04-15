package com.learning.telusko.service;

import com.learning.telusko.dao.ICourseRepo;
import com.learning.telusko.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService{
    @Autowired
    private ICourseRepo courseRepo;

    @Override
    public String createCourse(Course course) {
        return "";
    }

    @Override
    public String removeCourse(Course course) {
        return "";
    }

    @Override
    public Course getCourseDetailsById(int id) {
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        return List.of();
    }

    @Override
    public String updateCourse(Course course) {
        return "";
    }
}
