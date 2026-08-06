package com.fitness.activityservice.controller;


import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.service.ActivityService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {


    @Autowired // or use @AllArgsConstrctor
    private ActivityService activityService;

    @GetMapping("/getUserActivities")
    public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader("X-User-ID") String userID){
        return ResponseEntity.ok(activityService.getUserActivities(userID));

    }

    @PostMapping("/register")
    public ResponseEntity<ActivityResponse>trackActivity(@RequestBody ActivityRequest activityRequest, @RequestHeader("X-User-ID") String userID){
       if(userID != null){
           activityRequest.setUserId(userID);
       }
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivity(@PathVariable String activityId){
        return ResponseEntity.ok(activityService.getActivity(activityId));

    }

}
