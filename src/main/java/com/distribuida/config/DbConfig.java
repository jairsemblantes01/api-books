package com.distribuida.config;

import com.distribuida.servicios.BookRepository;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.sql.DataSource;

@ApplicationScoped
public class DbConfig {
    //Opcion2
    @Inject
    @ConfigProperty(name="db.user")
    private String dbUser;
    @Inject
    @ConfigProperty(name="db.password")
    private String dbPassword;
    @Inject
    @ConfigProperty(name="db.url")
    private String dbUrl;
    @Inject
    @ConfigProperty(name="db.driver")
    private String dbDriver;

    @Produces
    @ApplicationScoped
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(dbDriver);
        ds.setJdbcUrl(dbUrl);
        ds.setUsername(dbUser);
        ds.setPassword(dbPassword);

        return ds;
    }

    @Produces
    @ApplicationScoped
    public Jdbi jdbi(DataSource dataSource) {
        Jdbi ret = Jdbi.create(dataSource);
        ret.installPlugin(new SqlObjectPlugin());
        return ret;
    }

    @Produces
    @ApplicationScoped
    public BookRepository bookRepository(Jdbi jdbi) {
        return jdbi.onDemand(BookRepository.class);
    }
}
