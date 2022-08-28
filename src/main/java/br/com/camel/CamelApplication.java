package br.com.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CamelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelApplication.class, args);
	}
	
	@Component
	public class DebeziumRoute extends RouteBuilder{
	    
	    @Value("${my.offsetStorageFileName}")
	    private String offsetStorageFileName;
	    
	    @Value("${my.host}")
	    private String host;
	    
	    @Value("${my.username}")
	    private String username;
	    
	    @Value("${my.password}")
	    private String password;
	    
	    @Value("${my.db}")
	    private String db;

	    @Override
	    public void configure() throws Exception {
	        from("debezium-postgres:dbz-postgres?offsetStorageFileName="+offsetStorageFileName
	                +"&databaseHostName="+host
	                +"&databaseUser="+username
	                +"&databasePassword="+password
	                +"&databaseServerName="+db
	                +"&databaseDbname="+db
	                +"&pluginName=pgoutput")//decoderbufs
	        .log("EVENTO:           ${body}")
	        .log("Schema:           ${headers.CamelDebeziumIdentifier}")
	        .log("Source metadata:  ${headers.CamelDebeziumSourceMetadata}")
	        .log("Operação:         ${headers.CamelDebeziumOperation}")// tipo evento: c = create (or insert), u = update, d = delete, r = snapshot
	        .log("Base:             ${headers.CamelDebeziumSourceMetadata[db]}")
	        .log("Tabeça:           ${headers.CamelDebeziumSourceMetadata[table]}")
	        .log("Chave primária:   ${headers.CamelDebeziumKey}");
	        
	        System.out.println("databaseHostName="+host);
	    }
	    
	}

}
