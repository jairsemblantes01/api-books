package com.distribuida.config;

import io.helidon.config.Config;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
@ApplicationScoped
public class AppEventos {
    public void init(@Observes @Initialized(ApplicationScoped.class) Object event){
	
	    Config config = Config.create();
	    var url = config.get("db.connection.url").asString().get().toString();
			var user = config.get("db.connection.username").asString().get().toString();
	    var password = config.get("db.connection.password").asString().get().toString();
	
	
	    System.out.println("*****++migrando base de datos"+ url+user+ password);
        var flyway = Flyway.configure().dataSource(url, user, password)
                .load();
        flyway.baseline();
    }
}
