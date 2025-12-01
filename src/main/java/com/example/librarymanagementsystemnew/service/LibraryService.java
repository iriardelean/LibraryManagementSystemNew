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

    public Library createLibrary(Library lib) {
        return repository.save(lib);
    }

    public Optional<Library> getLibraryById(Long id) {
        return repository.findById(id);
    }

    public List<Library> getAllLibraries() {
        return repository.findAll();
    }

    public Library updateLibrary(Library lib) {
        if (lib == null || lib.getId() == null)
            throw new IllegalArgumentException("Library and Id cannot be null");
        return repository.save(lib);
    }

    public void deleteLibrary(Long id) {
        repository.deleteById(id);
    }
}

