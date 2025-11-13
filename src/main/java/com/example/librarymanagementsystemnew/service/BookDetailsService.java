package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.BookDetails;
import com.example.librarymanagementsystemnew.repository.BookDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDetailsService {

    private final BookDetailsRepository repository;

    public BookDetailsService(BookDetailsRepository repository) {
        this.repository = repository;
    }

    public BookDetails create(BookDetails entity) {
        if (entity == null)
            throw new IllegalArgumentException("BookDetails cannot be null");

        if (entity.getId() == null || entity.getId().isEmpty()) {
            String nextId = SequentialIdGenerator.getNextId(
                    repository.findAll(),
                    BookDetails::getId,
                    "book/det-");
            entity.setId(nextId);
        }
        return repository.save(entity);
    }

    public Optional<BookDetails> findById(String id) {
        return repository.findById(id);
    }

    public List<BookDetails> findAll() {
        return repository.findAll();
    }

    public BookDetails update(BookDetails entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException("BookDetails and Id cannot be null");
        repository.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("BookDetails with Id " + entity.getId() + " does not exist"));
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}
