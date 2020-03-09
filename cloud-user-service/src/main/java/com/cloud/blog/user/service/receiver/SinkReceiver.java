package com.cloud.blog.user.service.receiver;

import com.cloud.blog.user.service.Barista;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ClassName SinkReceiver
 * @Description
 * @Author wsail
 * @Date 2020/3/3 17:53
 **/

@Slf4j
@EnableBinding(Barista.class)
@Service
public class SinkReceiver {


    @StreamListener(Barista.INPUT_CHANNEL)
    public void receiver(Message message) throws IOException {

        Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        System.out.println("Input Stream 1 接受数据：" + message);
        System.out.println("消费完毕---------------");
        channel.basicAck(deliveryTag,false);

    }

}
