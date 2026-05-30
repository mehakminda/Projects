package com.learning.telusko.jobportal.dao
;

import com.learning.telusko.jobportal.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost,Integer> {

    List<JobPost> findByPostProfile(String postProfile);
    List<JobPost> findByPostProfileContainingOrPostDescContaining(String postProfile, String postDesc);

}
