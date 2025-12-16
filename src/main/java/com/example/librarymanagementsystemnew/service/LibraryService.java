package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.Library;
import com.example.librarymanagementsystemnew.repository.LibraryRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Library> searchLibraries(String name, String sortField, String sortDir) {
        Specification<Library> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();
        return repository.findAll(spec, sort);
    }
}