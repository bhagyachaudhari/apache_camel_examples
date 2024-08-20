package com.example.apache_camel_demo.mq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaSendMessage extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://test-kafka?period=10000")
                .setBody(simple("test kafka.."))
                .to("kafka:myKafkaTopic");
    }
}
