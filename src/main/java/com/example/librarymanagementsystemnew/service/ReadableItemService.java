package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.ReadableItem;
import com.example.librarymanagementsystemnew.repository.ReadableItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadableItemService {

    private final ReadableItemRepository repository;

    public ReadableItemService(ReadableItemRepository repository) {
        this.repository = repository;
    }

    public ReadableItem create(ReadableItem entity) {
        if (entity == null)
            throw new IllegalArgumentException("ReadableItem cannot be null");

        if (entity.getId() == null || entity.getId().isEmpty()) {
            String nextId = SequentialIdGenerator.getNextId(
                    repository.findAll(),
                    ReadableItem::getId,
                    "read/item-");
            entity.setId(nextId);
        }
        return repository.save(entity);
    }

    public Optional<ReadableItem> findById(String id) {
        return repository.findById(id);
    }

    public List<ReadableItem> findAll() {
        return repository.findAll();
    }

    public ReadableItem update(ReadableItem entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException("ReadableItem and Id cannot be null");
        repository.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("ReadableItem with Id " + entity.getId() + " does not exist"));
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}

