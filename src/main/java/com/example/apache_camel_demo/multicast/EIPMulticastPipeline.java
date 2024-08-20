package com.example.apache_camel_demo.multicast;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EIPMulticastPipeline extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet");
        rest("/payment")
                .get()
                .to("direct:payment-completed");

        from("direct:payment-completed")
                .setBody().constant("Payment completed for your online purchase")
                .multicast()
                .parallelProcessing()
                .to("direct:sender-bank-system")
                .to("direct:receiver-bank-system")
                .to("direct:online-shopping-system");

        from("direct:sender-bank-system")
                .log("${body}...${threadName}");
        from("direct:receiver-bank-system")
                .log("${body}...${threadName}");
        from("direct:online-shopping-system")
                .log("${body}...${threadName}");

    }
}
