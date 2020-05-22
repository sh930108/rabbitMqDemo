package com.rabbitmq.demo.fanout;

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
public class FanoutCounsumer {

    private final static String QUEUE_NAME = "fanout_queue_1";
    private final static String EXCHANGE_NAME = "topic.test1";
    private final static String ROUTING_KEY = "fanout_routing_key";

    public static void main(String[] args) throws Exception {
        /* 获取一个连接 */
        Connection connection = ConnectUtils.getConnect();
        /*从连接中创建通道*/
        final Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        //保证一次只分发一个
        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [1] fonout Received '" + message + "'");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("[1] Done");
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
