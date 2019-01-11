package com.voam.loggingconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import javax.xml.transform.Source;

@SpringBootApplication
@EnableBinding({ PreferredExchangeNameSink.class})
public class LoggingConsumerApplication {

    @Autowired
    CustomSource customSource;

    public static void main(String[] args) {
        SpringApplication.run(LoggingConsumerApplication.class, args);
    }

    @StreamListener(PreferredExchangeNameSink.INPUT)
    public void handle(Person person) {
        System.out.println("Received : " + person);

        FoodOrder foodOrder  = new FoodOrder();
        foodOrder.setRestaurant("Olive Garden");
        foodOrder.setName("Oli" + System.currentTimeMillis());

       customSource.output().send(MessageBuilder.withPayload(foodOrder).build());


    }



    public static class Person {
         private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


}
