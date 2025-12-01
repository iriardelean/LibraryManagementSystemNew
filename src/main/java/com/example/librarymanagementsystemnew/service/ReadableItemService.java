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

    public ReadableItem createReadableItem(ReadableItem entity) {
        return repository.save(entity);
    }

    public Optional<ReadableItem> getReadableItemById(Long id) {
        return repository.findById(id);
    }

    public List<ReadableItem> getAllReadableItem() {
        return repository.findAll();
    }

    public ReadableItem updateReadableItem(ReadableItem entity) {
        return repository.save(entity);
    }

    public void deleteReadableItem(Long id) {
        repository.deleteById(id);
    }
}