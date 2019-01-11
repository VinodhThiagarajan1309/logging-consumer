package com.voam.loggingconsumer;


import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(CustomSource.class)
public class RandomPublisher {
}
