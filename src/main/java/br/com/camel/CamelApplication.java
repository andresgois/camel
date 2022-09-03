package br.com.camel;

import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Component;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CamelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelApplication.class, args);
	}
	
	@Component
	class DebeziumPostgres extends RouteBuilder {
	    
	    private final Logger logger =  LoggerFactory.getLogger(DebeziumPostgres.class);

	    private String offsetStorageFileName = "C:\\Users\\andre\\eclipse-workspace\\camel\\files\\file.txt";
	    private String host = "localhost";
	    private String username = "postgres";
	    private String password = "123456";
	    private String db = "postgres";
	    
	    @Override
	    public void configure() throws Exception {
	        logger.info("=========================="+DebeziumPostgres.class+"==========================");
	        from("debezium-postgres:dbpost?offsetStorageFileName="+offsetStorageFileName
	                +"&databaseHostname="+host
	                +"&databaseUser="+username
	                +"&databasePort=5432"
	                +"&databasePassword="+password
	                +"&databaseServerName="+db
	                +"&databaseDbname="+db
	                +"&pluginName=pgoutput")//decoderbufs
	        .log("EVENTO:           ${body}")
	        .log("Schema:           ${headers.CamelDebeziumIdentifier}")
	        .log("Source metadata:  ${headers.CamelDebeziumSourceMetadata}")
	        .log("Operação:         ${headers.CamelDebeziumOperation}")// tipo evento: c = create (or insert), u = update, d = delete, r = snapshot
	        .log("Base:             ${headers.CamelDebeziumSourceMetadata[db]}")
	        .log("Tabela:           ${headers.CamelDebeziumSourceMetadata[table]}")
	        .log("Chave primária:   ${headers.CamelDebeziumKey}")
	        
	        .process(exchange -> {
//	           Struct body = exchange.getIn().getBody(Struct.class);
//	           Schema schema = body.schema();
//	           
//	           log.info("Body: "+body);
//	           log.info("Schema: "+schema);
//	           log.info("Campo: "+schema.fields());
//	           log.info("Campo nome: "+schema.field("name"));
	            
	           Map body = exchange.getIn().getBody(Map.class);
	           log.info("==============================================================================");
	           log.info("Body: "+body);
	           log.info("==============================================================================");
	        });
	        logger.info("==============================================================================");
	    }
	}

}
