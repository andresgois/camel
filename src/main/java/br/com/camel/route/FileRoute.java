package br.com.camel.route;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRoute extends RouteBuilder {

    private String path = "C:\\Users\\andre\\eclipse-workspace\\camel\\files\\";
    
    @Override
    public void configure() throws Exception {
        from("file://"+path+"input")//?delete=true")
        .log("Arquivo: ${header.CamelFileName} - Path: ${header.CamelFilePath}")
        .log("${file:name}")
        .bean("fileComponent")
        .process(new FileProcessor())
        .to("file://"+path+"output");
    }
    
}

@Component
class FileComponent {
    
    public void log(File file) {
        System.out.println("FileComponent: "+ file.getName());
    }
}

class FileProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("Processor: "+exchange.getIn().getBody());
    }
    
}
