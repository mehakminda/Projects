package com.learning.telusko.jobportal.service;



import com.learning.telusko.jobportal.dao.UserRepo;
import com.learning.telusko.jobportal.model.User;
import com.learning.telusko.jobportal.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            System.out.println("User not found : "+ username);
            throw new UsernameNotFoundException("User 404");
        }
        //we have to return user details not user
        //Userdetails is an interface

        return new UserPrincipal(user);
    }
}
