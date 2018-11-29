package com.rabbitmq.demo.roundrobin;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.demo.utils.ConnectUtils;

/**
 * @ClassName producer
 * @Description
 * @Author shanghao5
 * @Date 2018/11/27 19:58
 **/
public class Producer {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        /* 获取一个连接 */
        Connection connection = ConnectUtils.getConnect();

        /*从连接中创建通道*/
        Channel channel = connection.createChannel();

        //创建队列 (声明)  因为我们要往队列里面发送消息,这是后就得知道往哪个队列中发送,就好比在哪个管子里面放水,
        boolean durable=false;
        boolean exclusive=false;
        boolean autoDelete=false;
        //如果这个队列不存在,其实这句话是不需要的
        channel.queueDeclare(QUEUE_NAME, durable, exclusive, autoDelete, null);


        //第一个参数是exchangeName(默认情况下代理服务器端是存在一个""名字的exchange的,
        //因此如果不创建exchange的话我们可以直接将该参数设置成"",如果创建了exchange的话
        //我们需要将该参数设置成创建的exchange的名字),第二个参数是路由键
        for(int i=0;i < 100;i++){
            String msg="Hello  Simple QUEUE !"+ i;
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.out.println("[]"+i+"---------send ms :"+msg);

            Thread.sleep(10);
        }

        channel.close();
        connection.close();
    }


}
