package com.learning.telusko.jobportal.controller;



import com.learning.telusko.jobportal.model.User;
import com.learning.telusko.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/registerUser")
    public User register(@RequestBody User user){
        System.out.println("Adding user");
        return userService.saveuser(user);


    }
}

//user registeration