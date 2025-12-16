package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.BookDetails;
import com.example.librarymanagementsystemnew.repository.BookDetailsRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<BookDetails> searchBooks(String title, String isbn, Integer minPages, Integer maxPages, String sortField, String sortDir) {
        Specification<BookDetails> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (isbn != null && !isbn.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("isbn")), "%" + isbn.toLowerCase() + "%"));
            }
            if (minPages != null) {
                predicates.add(cb.ge(root.get("pageCount"), minPages));
            }
            if (maxPages != null) {
                predicates.add(cb.le(root.get("pageCount"), maxPages));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();

        return repository.findAll(spec, sort);
    }
}