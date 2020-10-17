package com.github.line.schedulereadonlyapi.repository.readonly;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadOnlyJpaRepository<T> extends JpaRepository<T, Long> {

    @Override
    public default void deleteById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public default void delete(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public default void deleteAll(Iterable<? extends T> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public default void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public default <S extends T> S save(S s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public default <S extends T> List<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public default void flush() {
        throw new UnsupportedOperationException();
    }

    @Override
    public default <S extends T> S saveAndFlush(S s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public default void deleteInBatch(Iterable<T> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public default void deleteAllInBatch() {
        throw new UnsupportedOperationException();
    }
}
