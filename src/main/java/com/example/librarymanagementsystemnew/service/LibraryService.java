package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.Library;
import com.example.librarymanagementsystemnew.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    private final LibraryRepository repository;

    public LibraryService(LibraryRepository repository) {
        this.repository = repository;
    }

    public LibraryService() {
        this(new LibraryRepository());
    }

    public Library create(Library lib) {
        if (lib == null)
            throw new IllegalArgumentException("Library cannot be null");
        return repository.save(lib);
    }

    public Optional<Library> findById(String id) {
        return repository.findById(id);
    }

    public List<Library> findAll() {
        return repository.findAll();
    }

    public Library update(Library lib) {
        if (lib == null || lib.getId() == null)
            throw new IllegalArgumentException("Library and Id cannot be null");
        return repository.save(lib);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}

