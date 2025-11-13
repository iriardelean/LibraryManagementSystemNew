package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.BookAuthor;
import com.example.librarymanagementsystemnew.repository.BookAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookAuthorService {

    private final BookAuthorRepository repository;

    public BookAuthorService(BookAuthorRepository repository) {
        this.repository = repository;
    }

    public BookAuthor create(BookAuthor entity) {
        if (entity == null)
            throw new IllegalArgumentException("BookAuthor cannot be null");
        return repository.save(entity);
    }

    public Optional<BookAuthor> findById(String id) {
        return repository.findById(id);
    }

    public List<BookAuthor> findAll() {
        return repository.findAll();
    }

    public BookAuthor update(BookAuthor entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException("BookAuthor and Id cannot be null");
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}

