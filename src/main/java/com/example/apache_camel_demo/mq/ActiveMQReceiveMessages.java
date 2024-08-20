package com.example.apache_camel_demo.mq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQReceiveMessages extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:test-mq")
                .log("log:receiving messages => ${body}");
    }
}
