package com.example.micro_service_panier.RabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    // Bean de ConnectionFactory pour se connecter à RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");  // Adresse du serveur RabbitMQ
        connectionFactory.setPort(5672);  // Port par défaut
        connectionFactory.setUsername("guest");  // Nom d'utilisateur RabbitMQ
        connectionFactory.setPassword("guest");  // Mot de passe RabbitMQ
        return connectionFactory;
    }

    // Bean RabbitTemplate configuré avec la ConnectionFactory
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());  // Utilisation du convertisseur JSON
        return rabbitTemplate;
    }

    // Déclaration de l'échange direct
    @Bean
    public Exchange sagaExchange() {
        return ExchangeBuilder.directExchange("saga-exchange").build();
    }

    // Message Converter pour JSON
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    // Déclaration des queues
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

    // Binding des queues à l'échange
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
