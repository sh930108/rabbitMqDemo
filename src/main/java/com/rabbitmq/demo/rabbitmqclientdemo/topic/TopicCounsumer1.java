package com.rabbitmq.demo.rabbitmqclientdemo.topic;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.utils.ConnectUtils;

import java.io.IOException;

/**
 * @ClassName FanoutCounsumer
 * @Description
 * @Author shanghao5
 * @Date 2018/11/28 16:13
 **/
public class TopicCounsumer1 {

    private final static String QUEUE_NAME = "topic_queue_2";
    private final static String EXCHANGE_NAME = "amq.topic";

    public static void main(String[] args) throws Exception {
        /* 获取一个连接 */
        Connection connection = ConnectUtils.getConnect();
        /*从连接中创建通道*/
        final Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic",true);
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"hello");


        //保证一次只分发一个
        channel.basicQos(1);
        DefaultConsumer consumer = null;
        consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("channel 1");
                System.out.println("Received :" + message + ";");
                System.out.println("appId :" + properties.getAppId());
                System.out.println("deliveryMode :" + properties.getDeliveryMode());
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };



        //手动确认消息
        boolean autoAck = false;
        //监听队列
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);

    }


}
