package br.com.camel;

import org.apache.camel.builder.RouteBuilder;
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
	    
	    private String offsetStorageFileName = "C:\\Users\\andre\\eclipse-workspace\\camel\\files\\offset-file.dat";
	    
	    private String host = "localhost";
	    
	    private String username = "postgres";
	    
	    private String password = "123456";
	    
	    private String db = "postgres";
	    

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
