package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.Author;
import com.example.librarymanagementsystemnew.model.BookAuthor;
import com.example.librarymanagementsystemnew.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorService() {
        this(new AuthorRepository());
    }

    public Author createAuthor(Author author) {
        if (author == null)
            throw new IllegalArgumentException("Author cannot be null");
        return authorRepository.save(author);
    }

    public Optional<Author> getAuthorById(String id) {
        return authorRepository.findById(id);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author updateAuthor(Author author) {
        if (author == null || author.getId() == null)
            throw new IllegalArgumentException("Author and Id cannot be null");
        return authorRepository.save(author);
    }

    public void deleteAuthor(String id) {
        authorRepository.delete(id);
    }

    public void addBookToAuthor(String authorId, BookAuthor bookAuthor) {
        var opt = authorRepository.findById(authorId);
        if (opt.isEmpty())
            throw new IllegalArgumentException("Author not found: " + authorId);
        Author author = opt.get();
        if (author.getBooks() == null) {
            author.setBooks(new java.util.ArrayList<>());
        }
        author.getBooks().add(bookAuthor);
        authorRepository.save(author);
    }
}

