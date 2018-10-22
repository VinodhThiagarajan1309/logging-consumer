package com.voam.loggingconsumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PreferredExchangeNameSink {

    String INPUT = "privin";

    @Input("privin")
    SubscribableChannel input();
}
