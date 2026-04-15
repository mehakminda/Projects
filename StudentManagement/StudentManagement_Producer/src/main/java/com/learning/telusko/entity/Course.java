package com.learning.telusko.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseID;
    private String courseName;
    private int courseDuration;
    private int courseFee;
    private boolean isActive;
    //private String courseInstructor; maybe later
}
