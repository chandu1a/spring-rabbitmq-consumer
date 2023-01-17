package com.chandula.rabbitmqconsumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitProperties {
    private String host;
    private int port;
    private String username;
    private String password;

}
