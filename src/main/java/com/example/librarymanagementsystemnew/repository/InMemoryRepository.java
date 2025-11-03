package com.example.librarymanagementsystemnew.repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class InMemoryRepository<T, ID> implements CrudRepository<T, ID> {

    private final Map<ID, T> store = new ConcurrentHashMap<>();
    private final Function<T, ID> idExtractor;

    public InMemoryRepository(Function<T, ID> idExtractor) {
        if (idExtractor == null) throw new IllegalArgumentException("idExtractor cannot be null");
        this.idExtractor = idExtractor;
    }

    @Override
    public T save(T entity) {
        if (entity == null) throw new IllegalArgumentException("entity cannot be null");
        ID id = idExtractor.apply(entity);
        if (id == null) throw new IllegalArgumentException("entity id cannot be null");
        store.put(id, entity);
        System.out.println("Saved: " + entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(ID id) {
        T removed = store.remove(id);
        if (removed != null) {
            System.out.println("Deleted: " + removed);
        } else {
            System.out.println("Item with ID " + id + " not found");
        }
    }
}
