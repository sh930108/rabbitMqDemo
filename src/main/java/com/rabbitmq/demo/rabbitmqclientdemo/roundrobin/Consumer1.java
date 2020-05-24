package com.rabbitmq.demo.rabbitmqclientdemo.roundrobin;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.utils.ConnectUtils;

import java.io.IOException;

/**
 * 简单队列
 *
 * @ClassName Consumer
 * @Description 耦合性高 生产消费一一对应(如果有多个消费者想都消费这个消息,就不行了) 队列名称变更时需要同时更改
 * @Author shanghao5
 * @Date 2018/11/27 19:59
 **/
public class Consumer1 {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        /* 获取一个连接 */
        Connection connection = ConnectUtils.getConnect();

        /*从连接中创建通道*/
        Channel channel = connection.createChannel();

        //声明队列  如果能确定是哪一个队列 这边可以删掉,不去掉 这里会忽略创建
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        final DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("============================");
                System.out.println(" [2] Received '" + message + "'");
                System.out.println("============================");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        //监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

//        channel.close();
//        connection.close();
    }

}
