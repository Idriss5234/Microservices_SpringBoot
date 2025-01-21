package com.example.micro_service_commande.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    // Bean de ConnectionFactory pour se connecter à RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("rabbitmq");  // Adresse du serveur RabbitMQ
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
        return QueueBuilder.durable("api1-producer-queue").build();
    }

    @Bean
    public Queue panierApiGetConsumerQueue() {
        return QueueBuilder.durable("api1-consumer-queue").build();
    }

    @Bean
    public Queue panierApiPostProducerQueue() {
        return QueueBuilder.durable("api2-producer-queue").build();
    }

    @Bean
    public Queue panierApiPostConsumerQueue() {
        return QueueBuilder.durable("api2-consumer-queue").build();
    }

    @Bean
    public Queue SuiviMssgQueue() {
        return QueueBuilder.durable("suivie-message-queue").build();
    }

    // Binding des queues à l'échange
    @Bean
    public Binding SuiviMssgQueueBinding() {
        return BindingBuilder.bind(SuiviMssgQueue()).to(sagaExchange())
                .with("suivi-message-queue-routing-key").noargs();
    }
    @Bean
    public Binding panierApiGetProducerBinding() {
        return BindingBuilder.bind(panierApiGetProducerQueue()).to(sagaExchange())
                .with("api1-producer-routing-key").noargs();
    }

    @Bean
    public Binding panierApiGetConsumerBinding() {
        return BindingBuilder.bind(panierApiGetConsumerQueue()).to(sagaExchange())
                .with("api1-consumer-routing-key").noargs();
    }

    @Bean
    public Binding panierApiPostProducerBinding() {
        return BindingBuilder.bind(panierApiPostProducerQueue()).to(sagaExchange())
                .with("api2-producer-routing-key").noargs();
    }

    @Bean
    public Binding panierApiPostConsumerBinding() {
        return BindingBuilder.bind(panierApiPostConsumerQueue()).to(sagaExchange())
                .with("api2-consumer-routing-key").noargs();
    }

    @Bean
    public Binding compensateApi1Binding() {
        return BindingBuilder.bind(compensateApi1Queue()).to(sagaExchange())
                .with("compensate-api1-routing-key").noargs();
    }

    @Bean
    public Binding compensateApi2Binding() {
        return BindingBuilder.bind(compensateApi2Queue()).to(sagaExchange())
                .with("compensate-api2-routing-key").noargs();}



    @Bean
    public Queue compensateApi1Queue() {
        return QueueBuilder.durable("compensate-api1-queue").build();
    }

    @Bean
    public Queue compensateApi2Queue() {
        return QueueBuilder.durable("compensate-api2-queue").build();
    }

}
