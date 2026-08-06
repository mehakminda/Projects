package com.fitness.aiservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Bean
    public TopicExchange fitnessExchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue activityQueue(){
        return new Queue(queue, true);
        //make it durable
    }

    @Bean
    public Binding activityBinding(TopicExchange fitnessExchange, Queue activityQueue){
        return BindingBuilder.bind(activityQueue).to(fitnessExchange).with(routingKey);
    }

    @Bean
    public MessageConverter jsonConverter() {
        return new JacksonJsonMessageConverter();
    }
}
