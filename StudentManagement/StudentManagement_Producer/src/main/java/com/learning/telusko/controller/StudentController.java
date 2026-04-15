package com.learning.telusko.controller;

import com.learning.telusko.service.ICourseService;
import com.learning.telusko.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private IStudentService studentService;
}
