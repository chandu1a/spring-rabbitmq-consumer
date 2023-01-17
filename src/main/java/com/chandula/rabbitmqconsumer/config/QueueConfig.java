package com.chandula.rabbitmqconsumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties()
@PropertySource(value = "file:${queue.config.file.path}", factory = YamlPropertySourceFactory.class)
public class QueueConfig {

    private List<String> registeredQueues = new ArrayList<>();

}
