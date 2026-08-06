package com.fitness.activityservice.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

/*    @Bean
    public Queue activityQueue(){
        return new Queue("activity.queue",true);
    }*/
    //check in rabbitmqconfig2
    //create a queue named activity.queue
    //durable truee=> the queue remains even if rabbitmq restarts

    //we want to convert the message that are being sent to json
    //convert java object to json before sending to rabbitmq
    // by default rabbitmq sends messages as raw binaries
    @Bean
    public MessageConverter jsonConverter() {
        return new JacksonJsonMessageConverter();
    }
}
