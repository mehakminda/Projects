package com.fitness.aiservice.model;



import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;


import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Activity {

    private String id;
    private String userId;
    private Integer duration;
    private String type;
    private Integer caloriesBurned;
    private LocalDateTime startTime;

    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Map<String, Object> additionalMetrics;

}

/***
 *
 * Also check auditing
 * Because you use @CreatedDate and @LastModifiedDate, auditing must be enabled with
 * @EnableMongoAuditing; otherwise those fields will not populate automatically.
 * Add it to your main application class or a configuration class:
 *
 * 1. ActivityServiceApplication  (or)
 * 2. Create a new class file MongoConfig and add there
 *
 *
 *
 *
 */