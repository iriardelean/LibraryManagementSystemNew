package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.Author;
import com.example.librarymanagementsystemnew.model.BookAuthor;
import com.example.librarymanagementsystemnew.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID; // Import für UUID

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    // KORREKT: Nur dieser Konstruktor wird benötigt.
    // Spring wird das @Repository-Bean automatisch injizieren.
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // ENTFERNT: Der parameterlose Konstruktor, der 'new AuthorRepository()' aufrief,
    // wurde entfernt, um die Dependency Injection zu ermöglichen.

    public Author createAuthor(Author author) {
        if (author == null)
            throw new IllegalArgumentException("Author cannot be null");
        // ID-Erstellung gehört in den Service-Layer
        if (author.getId() == null || author.getId().isEmpty()) {
            author.setId(UUID.randomUUID().toString());
        }
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

        // Sicherstellen, dass der Autor existiert, bevor er aktualisiert wird
        authorRepository.findById(author.getId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + author.getId()));

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