package com.distribuida.db;

import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.spi.DbMapperProvider;
import jakarta.annotation.Priority;

import java.util.Optional;
@Priority(1000)
public class BookMapperProvider implements DbMapperProvider {
    private static final BookMapper MAPPER = new BookMapper();
    @Override
    public <T> Optional<DbMapper<T>> mapper(Class<T> type) {
        if (type.equals(Book.class)) {
            return Optional.of((DbMapper<T>) MAPPER);
        }
        return Optional.empty();
    }
}