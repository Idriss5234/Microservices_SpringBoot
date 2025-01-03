package com.example.micro_service_commande.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
    public Queue commandeApiGetProducerQueue() {
        return QueueBuilder.durable("commande-api-get-producer-queue").build();
    }

    @Bean
    public Queue commandeApiGetConsumerQueue() {
        return QueueBuilder.durable("commande-api-get-consumer-queue").build();
    }

    @Bean
    public Queue commandeApiPostProducerQueue() {
        return QueueBuilder.durable("commande-api-post-producer-queue").build();
    }

    @Bean
    public Queue commandeApiPostConsumerQueue() {
        return QueueBuilder.durable("commande-api-post-consumer-queue").build();
    }

    @Bean
    public Queue compensatePanierQueue() {
        return QueueBuilder.durable("compensate-panier-queue").build();
    }

    // BINDING
    @Bean
    public Binding commandeApiGetProducerBinding() {
        return BindingBuilder.bind(commandeApiGetProducerQueue()).to(sagaExchange())
                .with("commande-api-get-producer-routing-key").noargs();
    }

    @Bean
    public Binding commandeApiGetConsumerBinding() {
        return BindingBuilder.bind(commandeApiGetConsumerQueue()).to(sagaExchange())
                .with("commande-api-get-consumer-routing-key").noargs();
    }

    @Bean
    public Binding commandeApiPostProducerBinding() {
        return BindingBuilder.bind(commandeApiPostProducerQueue()).to(sagaExchange())
                .with("commande-api-post-producer-routing-key").noargs();
    }

    @Bean
    public Binding commandeApiPostConsumerBinding() {
        return BindingBuilder.bind(commandeApiPostConsumerQueue()).to(sagaExchange())
                .with("commande-api-post-consumer-routing-key").noargs();
    }

    @Bean
    public Binding compensatePanierBinding() {
        return BindingBuilder.bind(compensatePanierQueue()).to(sagaExchange())
                .with("compensate-panier-routing-key").noargs();
    }
}
