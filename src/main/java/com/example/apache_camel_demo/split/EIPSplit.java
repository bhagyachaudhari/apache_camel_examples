package com.example.apache_camel_demo.split;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EIPSplit extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet");
        rest("/names")
                .post()
                .to("direct:names-list");

        from("direct:names-list")
                .transform().body(String.class)
                        .split(body()).delimiter(",")
                        .to("direct:print-name");

        from("direct:print-name")
                .to("log:print-name");
    }
}
