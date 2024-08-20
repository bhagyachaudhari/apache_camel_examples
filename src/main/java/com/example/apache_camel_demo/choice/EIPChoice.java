package com.example.apache_camel_demo.choice;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Controller;

@Controller
public class EIPChoice extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet");
        rest("/api")
                .post()
                .to("direct:choice-testing");

        from("direct:choice-testing")
                .choice()
                .when(body().contains("Hello World")).to("direct:hello-world-route")
                .when(simple("${body} contains 'testblog'")).to("direct:test-blog-route")
                .when(header("developer").isEqualTo("java")).to("direct:java-route")
                .otherwise().to("direct:dummy-route")
                .end();

        from("direct:hello-world-route")
                .log("Hello World Route!");
        from("direct:test-blog-route")
                .log("Test Blog Route!");
        from("direct:java-route")
                .log("Java Route!");
        from("direct:dummy-route")
                .log("Dummy Route!");
    }
}
