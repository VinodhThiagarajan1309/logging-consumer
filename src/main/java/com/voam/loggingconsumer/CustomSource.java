package com.voam.loggingconsumer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface CustomSource {


    String OUTPUT = "privout";

    @Output(OUTPUT)
    MessageChannel output();

}
