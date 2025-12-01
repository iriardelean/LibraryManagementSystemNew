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

    public BookDetails createBook(BookDetails entity) {
        return repository.save(entity);
    }

    public Optional<BookDetails> getBookById(Long id) {
        return repository.findById(id);
    }

    public List<BookDetails> getAllBooks() {
        return repository.findAll();
    }

    public BookDetails updateBook(BookDetails entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException("BookDetails and Id cannot be null");
        return repository.save(entity);
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}
