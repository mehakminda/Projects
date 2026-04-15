package com.learning.telusko.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollmentID;

    @Column(name = "student_id")
    private int studentID;

    @Column(name = "course_id")
    private int courseID;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Column(name = "grade")
    private String grade;

}
