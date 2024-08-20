package com.example.apache_camel_demo.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.language.simple.Simple;
import org.springframework.stereotype.Component;

@Component
public class FirstRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://test?period=5000")
                .setBody(simple("Welcome.."))
                .to("log:test");
    }
}
