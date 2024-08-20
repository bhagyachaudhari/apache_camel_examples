package com.example.apache_camel_demo.rest;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestProducerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet");
        rest("/testblogs")
                .get()
                .to("direct:test-blogs");

        from("direct:test-blogs")
                .transform().constant("Welcome..");
    }
}
