package com.codeHub.configs;

//import com.rabbitmq.client.ConnectionFactory;

import com.codeHub.service.AOConsumer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAutoConfiguration
public class RabbitMqConfig {


    private   @Value("${rabbitmq.queue}") String queueName;
    private static  @Value("${rabbitmq.username}") String username;
    private static  @Value("${rabbitmq.password}") String password;
    private static  @Value("${rabbitmq.virtual-host}") String virtualHost;
    private static  @Value("${rabbitmq.port}") int port;
    private static  @Value("${rabbitmq.host}") String host;

//    @Bean
//    Queue incentivesQueue() {
//        return new Queue("incentives", true);
//    }
//
//    @Bean
//    TopicExchange incentivesExchange() {
//        return new TopicExchange("incentives");
//    }
//
//    @Bean
//    Binding incentivesBinding(Queue incentivesQueue, TopicExchange incentivesExchange) {
//        return BindingBuilder.bind(incentivesQueue).to(incentivesExchange).with("incentives");
//    }

    @Bean
    Queue aoQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-max-priority", 10);
        return new Queue(queueName, true, false, false, args);
    }

    @Bean
    TopicExchange aoExchange() {
        return new TopicExchange(queueName);
    }

    @Bean
    Binding aoBinding(Queue aoQueue, TopicExchange aoExchange) {
        return BindingBuilder.bind(aoQueue).to(aoExchange).with(queueName);
    }

    //    @Bean
//    public ConnectionFactory rabbitConnectionFactory() {
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
//        cachingConnectionFactory.setUsername(username);
//        cachingConnectionFactory.setPassword(password);
//        cachingConnectionFactory.setVirtualHost(virtualHost);
//        cachingConnectionFactory.setPort(port);
//        return cachingConnectionFactory;
//    }
    @Bean
    public ConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        cachingConnectionFactory.setVirtualHost(virtualHost);
        cachingConnectionFactory.setPort(port);
        return cachingConnectionFactory;
    }

    //    @Bean
//    SimpleMessageListenerContainer incentivesContainer(ConnectionFactory connectionFactory,
//                                                       MessageListenerAdapter incentivesListenerAdapter) {
//        SimpleMessageListenerContainer incentivesContainer = new SimpleMessageListenerContainer();
//        incentivesContainer.setConnectionFactory(connectionFactory);
//        incentivesContainer.setQueueNames("incentives");
//        incentivesContainer.setMessageListener(incentivesListenerAdapter);
//        incentivesContainer.setConcurrentConsumers(50);
//        incentivesContainer.setPrefetchCount(12);
//        incentivesContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        return incentivesContainer;
//    }
    @Bean
    SimpleMessageListenerContainer aoContainer(ConnectionFactory connectionFactory,
                                               MessageListenerAdapter aoListenerAdapter) {
        SimpleMessageListenerContainer aoContainer = new SimpleMessageListenerContainer();
        aoContainer.setConnectionFactory(connectionFactory);
        aoContainer.setQueueNames(queueName);
        aoContainer.setMessageListener(aoListenerAdapter);
        aoContainer.setConcurrentConsumers(50);
        aoContainer.setPrefetchCount(12);
        aoContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return aoContainer;
    }

    @Bean
    MessageListenerAdapter aoListenerAdapter(AOConsumer aoConsumer) {
        return new MessageListenerAdapter(aoConsumer, "processAO");
    }
//
//    @Bean
//    MessageListenerAdapter incentivesListenerAdapter(IncentivesConsumerWorker incentivesConsumer) {
//        return new MessageListenerAdapter(incentivesConsumer, "processIncentives");
//    }
//
//    public void startMessageConsumers() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.start();
//    }

}