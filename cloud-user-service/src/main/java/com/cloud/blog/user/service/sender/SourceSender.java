package com.cloud.blog.user.service.sender;

import com.cloud.blog.user.service.Barista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;

/**
 * @ClassName SourceSender
 * @Description 发送消息
 * @Author wsail
 * @Date 2020/3/4 9:48
 **/

@EnableBinding(Barista.class)
public class SourceSender {


    @Autowired
    private Barista barista;

    public void send(Object object, Map<String,Object> properties){
        MessageHeaders headers = new MessageHeaders(properties);
        Message<Object> message = MessageBuilder.createMessage(object,headers);
        boolean sendStatus = barista.output().send(message);
        System.err.println(" ----------------- sending -------------------- ");
        System.out.println("发送数据: "+ object + ",sendStatus: "+ sendStatus);
    }
}
