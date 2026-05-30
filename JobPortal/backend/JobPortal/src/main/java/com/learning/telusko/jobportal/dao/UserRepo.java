package com.learning.telusko.jobportal.dao;



import com.learning.telusko.jobportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
