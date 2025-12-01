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
        return repository.save(entity);
    }

    public Optional<ReadableItem> findById(Long id) {
        return repository.findById(id);
    }

    public List<ReadableItem> findAll() {
        return repository.findAll();
    }

    public ReadableItem update(ReadableItem entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}