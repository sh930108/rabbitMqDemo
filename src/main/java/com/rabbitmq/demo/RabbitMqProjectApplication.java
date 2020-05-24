package com.rabbitmq.demo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class RabbitMqProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqProjectApplication.class, args);
    }
}
