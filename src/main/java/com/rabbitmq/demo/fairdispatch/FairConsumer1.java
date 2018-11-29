package com.rabbitmq.demo.fairdispatch;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.utils.ConnectUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 *
 * @ClassName Consumer
 * @Description
 * @Author shanghao5
 * @Date 2018/11/27 19:59
 **/
public class FairConsumer1 {

    private final static String QUEUE_NAME = "fair_dispatch";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        /* 获取一个连接 */
        Connection connection = ConnectUtils.getConnect();

        /*从连接中创建通道*/
        Channel channel = connection.createChannel();

        //声明队列  如果能确定是哪一个队列 这边可以删掉,不去掉 这里会忽略创建
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //保证一次只分发一个
        channel.basicQos(1);


        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [1] fair_dispatch Received '" + message + "'");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(" [x] Done");
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
