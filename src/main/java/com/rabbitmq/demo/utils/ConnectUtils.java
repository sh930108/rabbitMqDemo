package com.rabbitmq.demo.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ConnectUtil
 * @Description 获取连接的工具类
 * @Author shanghao5
 * @Date 2018/11/27 19:59
 **/
public class ConnectUtils {

    public  static final String HOST = "127.0.0.1";

    public static Connection getConnect() throws IOException, TimeoutException{
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost(HOST);
        //端口
        //amqp协议 端口 类似与mysql的3306
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/rabbit_cat");
        factory.setUsername("shanghao");
        factory.setPassword("123456");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

}
