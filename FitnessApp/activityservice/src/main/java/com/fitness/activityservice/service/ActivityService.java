package com.fitness.activityservice.service;


import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityService {

    private final ActivityRepository repository;
    private final UserValidationService userValidationService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${RabbitMQ.exchange.name}")
    private String exchange;

    @Value("${RabbitMQ.routing.key}")
    private String routingKey;

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {

        boolean isValidUser = userValidationService.validateUser(activityRequest.getUserId());

        if(!isValidUser)
            throw new RuntimeException("InValid userID : " +activityRequest.getUserId());


        Activity activity = Activity.builder()
                        .userId(activityRequest.getUserId())
                                .type(activityRequest.getType())
                                    .duration(activityRequest.getDuration())
                                            .caloriesBurned(activityRequest.getCaloriesBurned())
                                                    .startTime(activityRequest.getStartTime())
                                                            .additionalMetrics(activityRequest.getAdditionalMetrics())
                                                                    .build();

        Activity savedActivity = repository.save(activity);

        //publish to RabbitMq for Ai processing



        try{
            rabbitTemplate.convertAndSend(exchange,routingKey, savedActivity);

        }
        catch (Exception e) {
            log.error("Failed to publish activity to RabbitMq: ", e);
        }
        return mapToResponse(savedActivity);
    }

    private ActivityResponse mapToResponse(Activity activity){

        ActivityResponse activityResponse = new ActivityResponse();

        activityResponse.setId(activity.getId());
        activityResponse.setUserId(activity.getUserId());
        activityResponse.setType(activity.getType());
        activityResponse.setCaloriesBurned(activity.getCaloriesBurned());
        activityResponse.setDuration(activity.getDuration());
        activityResponse.setCreatedAt(activity.getCreatedAt());
        activityResponse.setUpdatedAt(activity.getUpdatedAt());
        activityResponse.setStartTime(activity.getStartTime());
        activityResponse.setAdditionalMetrics(activity.getAdditionalMetrics());

        return activityResponse;
    }

    public List<ActivityResponse> getUserActivities(String userId) {

       List<Activity> activities =  repository.findByUserId(userId);

       /*List<ActivityResponse> activityResponses = new ArrayList<>();

        activities.forEach((activity) ->
                activityResponses.add(mapToResponse(activity)));

       return activityResponses;*/


       return activities.stream()
               .map(this::mapToResponse)//call maptoResponse for every item of the list and collect in list
               .collect(Collectors.toList());

    }

    public ActivityResponse getActivity(String activityId) {

        return repository.findById(activityId)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + activityId));
                //why orElseThrow ? coz it returned an optional..learn more on this
    }
}


/**
 * Spring Boot does not automatically create exchanges or queues unless you explicitly define
 * them as Java @Bean components in your source code.
 *
 *
 * create them via spring boot/ manually in rabbitmq
 */