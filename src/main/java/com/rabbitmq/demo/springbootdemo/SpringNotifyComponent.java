package com.rabbitmq.demo.springbootdemo;


import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.PostConstruct;

public class SpringNotifyComponent {

    private ConnectionFactory rmqConnectionFactory = null;

    @PostConstruct
    public void initFactory(){
        //定义连接工厂
        com.rabbitmq.client.ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
        //设置服务地址
        factory.setHost("127.0.0.1");
        //端口
        //amqp协议 端口 类似与mysql的3306
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/rabbit_cat");
        factory.setUsername("shanghao");
        factory.setPassword("123456");
        rmqConnectionFactory =new CachingConnectionFactory(factory);
    }

    public RabbitTemplate getRabbitTemplete(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(rmqConnectionFactory);
        rabbitTemplate.setExchange("");
        rabbitTemplate.setQueue("");
        rabbitTemplate.setRoutingKey("");
        return rabbitTemplate;
    }

    public void sendMsg(){
        getRabbitTemplete().convertAndSend("hello");
    }


}
