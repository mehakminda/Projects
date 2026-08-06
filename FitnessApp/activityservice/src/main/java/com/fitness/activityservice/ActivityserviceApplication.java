package com.fitness.activityservice;

import com.mongodb.client.MongoClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@SpringBootApplication
//@EnableMongoAuditing
public class ActivityserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityserviceApplication.class, args);
    }

    //method1
  /*  @Bean
    CommandLineRunner check(MongoTemplate mongoTemplate) {
        return args -> System.out.println("DB = " +mongoTemplate.getDb().getName());
        //prints the database being used in Mongo Db
        //its printing test, which is fallback db
    }*/

   /* @Bean
    CommandLineRunner check(Environment env, MongoTemplate mongoTemplate) {
        return args -> {
            System.out.println("URI = " + env.getProperty("spring.data.mongodb.uri"));
            System.out.println("DB property = " + env.getProperty("spring.data.mongodb.database"));
            System.out.println("MongoTemplate DB = " + mongoTemplate.getDb().getName());
        };
    }*/


    //Workaround:Force the database name explicitly by adding this to your application class or a config class:
    //Method1
    //error I got:  Parameter 0 of method mongoDbFactory in com.fitness.activityservice.ActivityserviceApplication required a bean of type 'com.mongodb.client.MongoClient' that could not be found.
   /* @Bean
    MongoDatabaseFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, "fitness_activity_db");
    }*/

    //method2
    @Bean
    MongoDatabaseFactory mongoDbFactory() {
        return new SimpleMongoClientDatabaseFactory(
                "mongodb://localhost:27017/fitness_activity_db");
    }





}


/**
 * Method2:
 *
 * @Configuration
 * public class MongoCheckConfig {
 *
 *     @Bean
 *     CommandLineRunner check(MongoTemplate mongoTemplate) {
 *         return args -> System.out.println(mongoTemplate.getDb().getName());
 *     }
 * }
 */