package com.distribuida.config;
import io.helidon.dbclient.DbClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import io.helidon.config.Config;

@ApplicationScoped
public class DbConfig {


    @Produces
    @ApplicationScoped
    public static DbClient dbClient() {
        Config config = Config.create();
        Config dbConfig = config.get("db");

        // Client services are added through a service loader - see mongoDB example for explicit services
        DbClient dbClient = DbClient.builder(dbConfig)
                .build();
        return dbClient;
    }
}
