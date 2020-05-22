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
public class TopicCounsumer3 {

    private final static String QUEUE_NAME = "helloMqtt";
    private final static String EXCHANGE_NAME = "amq.topic";

    public static void main(String[] args) throws Exception {
        /* 获取一个连接 */
        Connection connection = ConnectUtils.getConnect();
        /*从连接中创建通道*/
        final Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // 绑定队列到交换机
//        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.query");
//        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.update");
//        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.delete");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.#");

        //保证一次只分发一个
        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("consumer[3] topic Received :" + message + "'");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("consumer[3] Done");
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
