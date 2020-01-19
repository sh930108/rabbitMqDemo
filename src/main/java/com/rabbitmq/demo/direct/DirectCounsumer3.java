package com.rabbitmq.demo.direct;

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
public class DirectCounsumer3 {

    private final static String QUEUE_NAME = "direct_queue_4";
    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws IOException, TimeoutException {
        /* 获取一个连接 */
        Connection connection = ConnectUtils.getConnect();
        /*从连接中创建通道*/
        final Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"delete");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"update");

        //保证一次只分发一个
        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");


            }
        };

        //手动确认消息
        boolean autoAck = true;
        //监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

    }


}
