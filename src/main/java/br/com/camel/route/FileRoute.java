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
        /*
        //from("file://"+path+"input")//?delete=true")
        from("file://"+path+"input?move=${date:now:yyyyMMdd}/${file:name}")
        .log("Arquivo: ${header.CamelFileName} - Path: ${header.CamelFilePath}")//?noop=true //impede de processar arquivos iguais | recursive=true //verifica subpastas
        // ?excludeExt=png,txt não processa dados com extersão png
        // ?timeInit=SECONDS&initialDelay=10 verifica se há algo na pasta 10 segundos depois | ?delay=1000&repeatCount=3
        // ?filterFile=${file:size} < 423 filtra por tamanho do arquivo que pode ser processado
        // ?recursive=true&delete=true com output?flatten=true   copia dados de subpastas na entrada e cola na raiz de output
        // ?includeExt=txt só vai processar arquivos txt
        .log("${file:name}")
        .choice()
            .when(simple("${header.CamelFileLength} < 422"))
                .to("bean:fileComponent")
            .otherwise()
                .process(new FileProcessor())                
        //.bean("fileComponent")
        .to("file://"+path+"output");
        */
        // FILE WATCH
        /*from("file-watch:"+path)//?events=MODIFY pega apenas os eventos de modificação
            .log("Evento: ${header.CamelFileEventType} Arquivo: ${header.CamelFileName}");*/
        
        from("direct:log-file")
            .log("Log: ${header.CamelFileName}")
            .process(new DirectProcess());
            
        from("file://"+path+"input")
            .to("direct:log-file");
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

class DirectProcess implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        File file = exchange.getIn().getBody(File.class);
        System.out.println("Processor: "+file.getName());
    }
}