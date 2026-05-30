package com.learning.telusko.jobportal.service;

import com.learning.telusko.jobportal.dao.JobRepo;
import com.learning.telusko.jobportal.model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class JobService {
    @Autowired
    JobRepo jobRepo;


    public JobPost getJobPost(int postId) {
        return jobRepo.findById(postId).orElse(new JobPost());
    }
    public List<JobPost> getAllJob(){
        return jobRepo.findAll();

    }
    public JobPost addJobPost(JobPost jobPost){
        return jobRepo.save(jobPost);

    }

    public JobPost updateJobPost(JobPost jobPost){
        return jobRepo.save(jobPost);

    }
    public void deleteJobPost(int postId){
         jobRepo.deleteById(postId);
    }

    public List<JobPost>  searchByKeyword(String keyword){
        return  jobRepo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
    }

    public void loadData(){

        List<JobPost> jobs = new ArrayList<>(Arrays.asList(
                new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
                        List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),
                new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React",
                        3, List.of("HTML", "CSS", "JavaScript", "React")),
                new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
                        List.of("Python", "Machine Learning", "Data Analysis")),
                new JobPost(4, "Full Stack Developer", "Experience in both front-end and back-end development",
                        5, List.of("JavaScript", "Node.js", "React", "Spring", "MongoDB")),
                new JobPost(5, "Cloud Architect", "Design and implement cloud infrastructure solutions", 6,
                        List.of("Cloud Computing", "AWS", "Azure", "Google Cloud"))

        ));

        jobRepo.saveAll(jobs);
    }
}
