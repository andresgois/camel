package br.com.camel.route;

import org.apache.camel.builder.RouteBuilder;

public class FileRoute extends RouteBuilder {

    private String path = "C:\\Users\\andre\\eclipse-workspace\\camel\\files\\";
    
    @Override
    public void configure() throws Exception {
       
        from("timer:my-timer?period=2000&repeatCount=2&time=2022-08-27 16:14:40")
            .log("Hora: ${date:now:HH:mm:ss}");
    }
    
}
