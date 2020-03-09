package com.cloud.blog.user.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @ClassName Barista
 * @Description TODO
 * @Author wsail
 * @Date 2020/3/4 12:07
 **/
public interface Barista {

    String OUTPUT_CHANNEL = "output_channel";
    String INPUT_CHANNEL = "input_channel";

    @Output(Barista.OUTPUT_CHANNEL)
    MessageChannel output();

    @Input(Barista.INPUT_CHANNEL)
    SubscribableChannel input();
}
