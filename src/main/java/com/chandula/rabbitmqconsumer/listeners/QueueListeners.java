package com.chandula.rabbitmqconsumer.listeners;

import com.chandula.rabbitmqconsumer.config.QueueConfig;
import com.chandula.rabbitmqconsumer.config.RabbitProperties;
import com.chandula.rabbitmqconsumer.messages.TestMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueListeners {

    private final QueueConfig queueConfig; // This is accessed by the Spel notation by the RabbitListener

    @Autowired
    public QueueListeners(QueueConfig queueConfig) {
        this.queueConfig = queueConfig;
    }

    @Bean
    ConnectionFactory connectionFactory(RabbitProperties rabbitProperties) {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(rabbitProperties.getHost());
        cachingConnectionFactory.setUsername(rabbitProperties.getUsername());
        cachingConnectionFactory.setPassword(rabbitProperties.getPassword());
        cachingConnectionFactory.setPort(rabbitProperties.getPort());
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter converter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @RabbitListener(queues = "#{queueConfig.getRegisteredQueues}", concurrency = "${queue.listener.concurrency}", ackMode = "MANUAL")
    public void listen(Channel channel, TestMessage testMessage) {
        log.info(testMessage.toString());
    }

}
