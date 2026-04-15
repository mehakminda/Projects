package com.learning.telusko.service;

import com.learning.telusko.entity.Course;

import java.util.List;

public interface ICourseService {
    public String createCourse(Course course);
    public String removeCourse(Course course);
    public Course getCourseDetailsById(int id);
    public List<Course> getAllCourses();
    public String updateCourse(Course course);
}
