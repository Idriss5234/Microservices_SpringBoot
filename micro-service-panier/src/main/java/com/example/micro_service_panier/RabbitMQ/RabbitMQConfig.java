package com.example.micro_service_panier.RabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate();  // or use constructor-based dependency injection if needed
    }

    // Declare the direct exchange
    @Bean
    public Exchange sagaExchange() {
        return ExchangeBuilder.directExchange("saga-exchange").build();
    }

    // Message Converter for JSON
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    // QUEUES
    @Bean
    public Queue panierApiGetProducerQueue() {
        return QueueBuilder.durable("panier-api-get-producer-queue").build();
    }

    @Bean
    public Queue panierApiGetConsumerQueue() {
        return QueueBuilder.durable("panier-api-get-consumer-queue").build();
    }

    @Bean
    public Queue panierApiPostProducerQueue() {
        return QueueBuilder.durable("panier-api-post-producer-queue").build();
    }

    @Bean
    public Queue panierApiPostConsumerQueue() {
        return QueueBuilder.durable("panier-api-post-consumer-queue").build();
    }

    // BINDING
    @Bean
    public Binding panierApiGetProducerBinding() {
        return BindingBuilder.bind(panierApiGetProducerQueue()).to(sagaExchange())
                .with("panier-api-get-producer-routing-key").noargs();
    }

    @Bean
    public Binding panierApiGetConsumerBinding() {
        return BindingBuilder.bind(panierApiGetConsumerQueue()).to(sagaExchange())
                .with("panier-api-get-consumer-routing-key").noargs();
    }

    @Bean
    public Binding panierApiPostProducerBinding() {
        return BindingBuilder.bind(panierApiPostProducerQueue()).to(sagaExchange())
                .with("panier-api-post-producer-routing-key").noargs();
    }

    @Bean
    public Binding panierApiPostConsumerBinding() {
        return BindingBuilder.bind(panierApiPostConsumerQueue()).to(sagaExchange())
                .with("panier-api-post-consumer-routing-key").noargs();
    }
}
