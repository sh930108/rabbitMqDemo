package com.rabbitmq.demo.springbootdemo.consumer;

import com.rabbitmq.demo.constant.ConsumerInfo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AnnotationConsumer {


//    @RabbitListener(queues="hello")
//    public void receive(Message message){
//        System.out.println(Arrays.toString(message.getBody()));
//        System.out.println(message.getMessageProperties());
//    }

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory",
            bindings = @QueueBinding(exchange = @Exchange(value = ConsumerInfo.TEST_EXCHANGE,
                    durable = "true", autoDelete = "false"),
                    value = @Queue(value = ConsumerInfo.TEST_QUEUE,
                            durable = "true", autoDelete = "false", exclusive = "false"),
                    key = ConsumerInfo.TEST_BINGKEY))
    public void receive2(Message message){
        System.out.println(Arrays.toString(message.getBody()));
        System.out.println(message.getMessageProperties());
    }
}
