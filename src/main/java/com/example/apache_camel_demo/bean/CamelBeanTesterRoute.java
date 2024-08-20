package com.example.apache_camel_demo.bean;

import org.apache.camel.Handler;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.apache.camel.component.bean.BeanConstants.BEAN_METHOD_NAME;

@Component
public class CamelBeanTesterRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:myTimer?period=10_000")
                .setBody(e->new Date())
                .setHeader("myHeader", ()-> "JSS")
                //.setHeader(BEAN_METHOD_NAME, ()->"fromClient")
                .bean(MyBean.class, "fromServer(${body}, ${header.myHeader})")
                //.bean(new MyBean())
                //.bean("com.example.apache_camel_demo.bean.MyBean?method=fromServer")
                //.bean("com.example.apache_camel_demo.bean.MyBean")
                //.bean("com.example.apache_camel_demo.bean.MyBean?method=fromServer(${body}, ${header.myHeader})")
                .log(LoggingLevel.INFO, "${body}");
    }
}

class MyBean{
    public String fromClient(String msg){
        return "From Client: "+msg;
    }

    public String fromServer(String body, String header){
        return "From Server=> body: "+body+" header: "+header;
    }

    @Handler
    public String fromDefault(String msg){
        return "From Default: "+msg;
    }
}
