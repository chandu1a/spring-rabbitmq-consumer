package com.chandula.rabbitmqconsumer.messages;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestMessage implements Serializable {
    private String id;
    private String message;
}
