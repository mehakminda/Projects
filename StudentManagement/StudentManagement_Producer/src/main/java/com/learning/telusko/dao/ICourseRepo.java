package com.learning.telusko.dao;

import com.learning.telusko.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepo extends JpaRepository<Course, Integer> {
}
