package com.rabbitmq.demo.topic;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.utils.ConnectUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName FanoutCounsumer
 * @Description
 * @Author shanghao5
 * @Date 2018/11/28 16:13
 **/
public class TopicCounsumer2 {

    private final static String QUEUE_NAME = "topic_queue_2";
    private final static String EXCHANGE_NAME = "exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        /* 获取一个连接 */
        Connection connection = ConnectUtils.getConnect();
        /*从连接中创建通道*/
        final Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.delete");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.update");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.error");

        //保证一次只分发一个
        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("consumer[2] topic Received :" + message + "'");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("consumer[2] Done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }

            }
        };

        //手动确认消息
        boolean autoAck = false;
        //监听队列
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);

    }


}
