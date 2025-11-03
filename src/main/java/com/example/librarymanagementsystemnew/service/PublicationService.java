package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.Publication;
import com.example.librarymanagementsystemnew.repository.PublicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {

    private final PublicationRepository repository;

    public PublicationService(PublicationRepository repository) {
        this.repository = repository;
    }

    // default constructor uses in-memory repository
    public PublicationService() {
        this(new PublicationRepository());
    }

    public Publication create(Publication entity) {
        if (entity == null)
            throw new IllegalArgumentException("Publication cannot be null");
        return repository.save(entity);
    }

    public Optional<Publication> findById(String id) {
        return repository.findById(id);
    }

    public List<Publication> findAll() {
        return repository.findAll();
    }

    public Publication update(Publication entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException("Publication and Id cannot be null");
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}
