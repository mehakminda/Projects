package com.learning.telusko.jobportal.controller;

import com.learning.telusko.jobportal.model.JobPost;
import com.learning.telusko.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {
    @Autowired
    JobService jobService;


    @GetMapping(path="/jobPost/{postId}",produces={"application/json"}) //gives only json ,cannot give xml . We also have consumes, add jackson xml
    public JobPost getAJobPost(@PathVariable int postId) {
        return (JobPost) jobService.getJobPost(postId);
    }

    @GetMapping("/getAllJobPosts")
    public List<JobPost> getAllJob() {

        return jobService.getAllJob();
    }

    @PostMapping("jobPost")
    public JobPost addJob(@RequestBody JobPost jobPost){
        jobService.addJobPost(jobPost);
        return jobService.getJobPost(jobPost.getPostId());
    }

    @PutMapping ("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        jobService.updateJobPost(jobPost);
        return jobService.getJobPost(jobPost.getPostId());
    }

    @DeleteMapping ("jobPost/{postId}")
    public String deleteJob(@PathVariable int postId){
        jobService.deleteJobPost(postId);
        return "Deleted Successfully";
    }

    @GetMapping("jobPost/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable String keyword){
        return jobService.searchByKeyword(keyword);
    }

    @GetMapping("/load")
    public String loadData(){
        jobService.loadData();
        return "success";
    }


}
