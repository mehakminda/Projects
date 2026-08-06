package com.fitness.activityservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig2 {

    @Value("${RabbitMQ.exchange.name}")
    private String exchangeName;

    @Value("${RabbitMQ.queue.name}")
    private String queueName;

    @Value("${RabbitMQ.routing.key}")
    private String routingKey;

    // 1. Define the Exchange
    @Bean
    public TopicExchange fitnessExchange() {
        return new TopicExchange(exchangeName);
    }

    // 2. Define the Queue
    @Bean
    public Queue activityQueue() {
        return new Queue(queueName, true); // true makes it durable
    }

    // 3. Bind the Queue to the Exchange using the Routing Key
    @Bean
    public Binding activityBinding(Queue activityQueue, TopicExchange fitnessExchange) {
        return BindingBuilder.bind(activityQueue).to(fitnessExchange).with(routingKey);
    }
}

/**
 * Why did we add this..
 * coz while publishing message to the queue I got below error
 *
 * reply-code=404, reply-text=NOT_FOUND - no exchange 'fitness.exchange' in vhost '/'...
 *
 * The property placeholder is now resolving correctly to fitness.exchange, but the
 * application is crashing because that exchange does not exist on your RabbitMQ broker.
 *
 * Spring Boot does not automatically create exchanges or queues unless you explicitly define
 * them as Java @Bean components in your source code.
 *
 * Add a configuration class to your project. This tells Spring to check for the exchange and queue
 * on startup, and automatically create them if they are missing.
 *
 *
 *
 * will this configuration class create the existing exchanges queues/bindingd?
 *
 * No it will not overwrite or disrupt any existing exchanges, queues, or bindings.
 * Spring AMQP checks the RabbitMQ broker first before executing any creation commands.🛡️
 *
 * How Spring Handles Existing InfrastructureIdempotent
 *
 * Operation: Spring uses the passive declaration method
 * (queueDeclarePassive and exchangeDeclarePassive) behind the scenes.Matches
 * Exactly: If the exchange or queue already exists with the same configuration
 * (e.g., name, durability), Spring simply does nothing and reuses it.
 * No Data Loss: Existing messages inside your queues are completely safe and will not be
 * deleted or cleared.
 */

// We can combine RabbeMqConfig1 and RabbeMqConfig2 class