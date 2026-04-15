package com.learning.telusko.controller;

import com.learning.telusko.entity.Course;
import com.learning.telusko.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @PostMapping("/createCourse")
    public String createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    @GetMapping("/getCourseById/{id}")
    public Course getCourseById(@PathVariable int id){
        return courseService.getCourseDetailsById(id);
    }

    @GetMapping("/getAllCourse")
    public List<Course> getAllCourse(){
        return courseService.getAllCourses();
    }


    @DeleteMapping("/removeCourse/{id}")
    public Course removeCourse(@PathVariable int id){
        return courseService.removeCourse()
    }

    public String deActivateCourse(){

    }



}
