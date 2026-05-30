package com.learning.telusko.jobportal.service;



import com.learning.telusko.jobportal.dao.UserRepo;
import com.learning.telusko.jobportal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    //this is method1 or we can create a bean in secuirty configuration


    public User saveuser(User user){
        //before storing password in database we must hash it
        // so we will change password from the user object
        System.out.println("old password: " + user.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println("new encoded password: " +user.getPassword());

        return userRepo.save(user);
    }
}
//We can combine this class into MyUserDetailsService class

//how to encode password while registering
//login with a user who has encoded password in db , how to pass the encoded password from postman