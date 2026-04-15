package com.learning.telusko.dao;

import com.learning.telusko.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepo extends JpaRepository<Student, Integer> {
}
