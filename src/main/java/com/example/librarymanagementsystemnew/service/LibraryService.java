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

    public Library create(Library lib) {
        if (lib == null)
            throw new IllegalArgumentException("Library cannot be null");

        if (lib.getId() != null && repository.findById(lib.getId()).isPresent()) {
            String nextId = SequentialIdGenerator.getNextId(
                    repository.findAll(),
                    Library::getId,
                    "lib-");
            lib.setId(nextId);
        }
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
        repository.findById(lib.getId())
                .orElseThrow(() -> new IllegalArgumentException("Library with Id " + lib.getId() + " does not exist"));
        return repository.save(lib);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}

