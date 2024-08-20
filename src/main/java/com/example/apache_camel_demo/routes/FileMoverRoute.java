package com.example.apache_camel_demo.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileMoverRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file://C:\\Bhagyashri\\Courses\\Apache Camel\\data\\source")
                .log("${headers}")
                .log("${body}")
                .to("file://C:\\Bhagyashri\\Courses\\Apache Camel\\data\\dest");
    }
}
