package com.example.apache_camel_demo.routes;

import com.example.apache_camel_demo.processor.MaskSensitiveInfo;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileHandlerRoute extends RouteBuilder {

    public static final String APPEND = "&fileExist=Append";
    @Override
    public void configure() throws Exception {
        from("file://C:\\Bhagyashri\\Courses\\Apache Camel\\data\\secure\\source\\?fileName=camel-demo-in.txt")
                .log(LoggingLevel.ERROR, ">> ${body}")
                .process(new MaskSensitiveInfo())
                .to("file://C:\\Bhagyashri\\Courses\\Apache Camel\\data\\secure\\dest\\?fileName=camel-demo-out.txt" + APPEND);

    }
}
