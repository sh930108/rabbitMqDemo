package com.rabbitmq.demo.rabbitmqclientdemo.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.demo.utils.ConnectUtils;

/**
 * @ClassName TopicProducer
 * @Description
 * @Author shanghao5
 * @Date 2018/11/28 17:08
 **/
public class TopicProducer {


    private final static String EXCHANGE_NAME = "amq.topic";

    public static void main(String[] argv) throws Exception {
        /* 获取一个连接 */
        Connection connection = ConnectUtils.getConnect();
        /*从连接中创建通道*/
        Channel channel = connection.createChannel();

        // 声明exchange
//        channel.exchangeDeclare(EXCHANGE_NAME, "topic",true);
//        BasicProperties basicProperties = new AMQP.BasicProperties();


        // 消息内容
        String message = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111\n" +
                "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111\n" +
                "111111111111111111111111";
        channel.basicPublish(EXCHANGE_NAME, "helloMqtt", null, message.getBytes());
//        channel.basicPublish(EXCHANGE_NAME, "item.query", null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");

        Thread.sleep(100000);
        channel.close();
        connection.close();
    }
}
