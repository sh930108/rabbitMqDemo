package com.rabbitmq.demo.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.demo.utils.ConnectUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName PSProducer
 * @Description 扇形交换机 该交换机会把消息发送到所有binding到该交换机上的queue。这种是publisher/subcribe模式。用来做广播最好。
 *              所有该exchagne上指定的routing-key都会被ignore掉
 *
 * @Author shanghao5
 * @Date 2018/11/28 16:07
 **/
public class PSProducer {

    private final static String EXCHANGE_NAME = "topic.test1";
    private final static String ROUTING_KEY = "";

    public static void main(String[] args) throws Exception {
        /* 获取一个连接 */
        Connection connection = ConnectUtils.getConnect();
        /*从连接中创建通道*/
        Channel channel = connection.createChannel();
        //fanout 分裂
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout",true,false,null);

        // 消息内容
        String message = "Hello exhcange fonout";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
        System.out.println(" [produce] Send '" + message + "'");

        channel.close();
        connection.close();

    }




}
