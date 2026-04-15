package com.learning.telusko.service;

import com.learning.telusko.dao.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService{
    @Autowired
    private IStudentRepo studentRepo;
}
