package com.rabbitmq.demo.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.demo.utils.ConnectUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName PSProducer
 * @Descriptio
 *
 * @Author shanghao5
 * @Date 2018/11/28 16:07
 **/
public class PSProducer {

    private final static String EXCHANGE_NAME = "test_exchange_direct";
    private final static String ROUTING_KEY = "error";

    public static void main(String[] args) throws IOException, TimeoutException {
        /* 获取一个连接 */
        Connection connection = ConnectUtils.getConnect();
        /*从连接中创建通道*/
        Channel channel = connection.createChannel();
        //fanout 分裂
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        // 消息内容
        String message = "Hello exhcange fonout";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
        System.out.println(" [produce] Send '" + message + "'");

        channel.close();
        connection.close();

    }




}
