
# Spring Stream Cloud Messaging using RabbitMq as Messaging Middleware

## How is it all wired ?

Few lines of code is all it takes to take our messaging implementation to Prod with *Spring Cloud Stream* project from *Spring*. We can use messaging middleware as *RabbitMQ* or *Kafka*. This example is explained using *RabbitMq*

### Sink AKA Exchange in Rabbit
- In order to create an sink or exchange in the RabbitMq, just create an interface, Spring's *Auto-Config* will implement this for you.
```
public interface MyExchangeNameSink {

    String INPUT = "myExchangeName";

    @Input("myExchangeName")
    SubscribableChannel input();
}


```
- Then add *@EnableBinding* annotation to the Enable this bindings to your Exchanges and Queues. You can initiate any many bindings with Sinks(Exchanges) you want.

```
@SpringBootApplication
@EnableBinding({Sink.class,MyExchangeNameSink.class})
public class LoggingConsumerApplication {
```

- When you start the application as such, you can see the queue getting created in the *INPUTNAME.anonymous.SOMEHASHVALUE* format like this
```
myexchangename.anonymous.zSQXU0cqTRO_acQ3PLDFZQ
```
where ***anonymous.zSQXU0cqTROacQ3PLDFZQ*** is called the GROUP NAME/QUEUE NAME. You can give a more valid name to it by adding a entry in the *application.properties* file as follows,
```
spring.cloud.stream.bindings.<Your_Exchange_Variable_Name>.group=PrefferedGroupName
```
In our care the variable name is ***myExchangeName*** hence the resolved property will be like,

```
spring.cloud.stream.bindings.myExchangeName.group=sampleGroup

```
where *myExchangeName* is the name of the SINK/EXCHANGE

Likewise you can also provide a valid exchange name,

```
spring.cloud.stream.bindings.<Your_Exchange_Variable_Name>.destination=exchangeName
```

![EDgG_Lr86-clipboard.png](https://i.postimg.cc/QVGjDsdB/EDg-G-Lr86-clipboard.png)
![43-06Xfps-clipboard.png](https://i.postimg.cc/6qGBFTtJ/43-06-Xfps-clipboard.png)


### Stream Listener AKA Subscriber AKA Message Handler

Now that we have a Exchange and a Queue Created we need to publish a message and see if we can consume it.

- Lets first create a Sample class namely Coordinates
```
public static class Coordinates {
        private float latitude;

        public float getLongitude() {
            return longitude;
        }

        public void setLongitude(float longitude) {
            this.longitude = longitude;
        }

        private float longitude;

        @Override
        public String toString() {
            return "Coordinates{" +
                    "latitude=" + latitude +
                    ", longitude=" + longitude +
                    '}';
        }
    }

```

- Now lets create a subscriber with a single annotation. We need to specify from which Sink we are going to listen from. Where the input to the annotation is the name of the Exchange. Then *handle* method can now receive *Coordinates* object

```
@StreamListener(MyExchangeNameSink.INPUT)
    public void handle(Coordinates coordinates) {
        System.out.println("Received : " + coordinates);
    }
```
- After starting the application from the Rabbit Client lets submit a sample message in Coordinates format. The following will be printed in the console


***INPUT***
```
{
"latitude" : 12.48 ,
"longitude" : 38.42
}
```
***OUTPUT***
```
Received : Coordinates{latitude=12.48, longitude=38.42}
```

### Vinodh Thiagarajan @ Intersys Inc

