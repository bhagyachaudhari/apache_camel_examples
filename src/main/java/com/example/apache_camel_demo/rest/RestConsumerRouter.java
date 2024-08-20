package com.example.apache_camel_demo.rest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestConsumerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://test-rest-api?period=10000")
                .log("Rest API calling...")
                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .to("http://localhost:8080/hello?sleepTimeMills=1000")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String output = exchange.getIn().getBody(String.class);
                        System.out.println("Rest Call response is: "+output);
                    }
                });
    }
}
