package com.fitness.activityservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "activities") //name of collection in mongo db
@Data
@Builder //from lombok
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    @Id
    private String id;
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Field("metrics") //custom name to this field
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