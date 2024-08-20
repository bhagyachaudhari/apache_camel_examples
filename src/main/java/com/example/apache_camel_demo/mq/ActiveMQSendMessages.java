package com.example.apache_camel_demo.mq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQSendMessages extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://test?period=10000")
                .setBody(simple("this is test message..."))
                .to("activemq:test-mq");
    }
}
