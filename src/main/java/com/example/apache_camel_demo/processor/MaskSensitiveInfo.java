package com.example.apache_camel_demo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MaskSensitiveInfo implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        String line = exchange.getMessage().getBody(String.class);
        System.out.println("line: " + line);
        if (line.toLowerCase().startsWith("password:") || line.toLowerCase().startsWith("pwd:")) {
            System.out.println("line.toLowerCase(): " + line.toLowerCase());
            line = line.substring(0, line.indexOf(":")) + ": *****";
            System.out.println("line2---"+line);

            exchange.getMessage().setBody(line);
        }
    }
}
