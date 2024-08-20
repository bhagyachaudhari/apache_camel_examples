package com.example.apache_camel_demo.mq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiverMessage extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("kafka:myKafkaTopic")
                .log("Message from Kafka Consumer => ${body}");
    }
}
