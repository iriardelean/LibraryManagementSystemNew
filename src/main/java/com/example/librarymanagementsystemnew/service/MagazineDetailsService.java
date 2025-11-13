package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.Author;
import com.example.librarymanagementsystemnew.model.MagazineDetails;
import com.example.librarymanagementsystemnew.repository.MagazineDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagazineDetailsService {

    private final MagazineDetailsRepository repository;

    public MagazineDetailsService(MagazineDetailsRepository repository) {
        this.repository = repository;
    }

    public MagazineDetails create(MagazineDetails entity) {
        if (entity == null)
            throw new IllegalArgumentException("MagazineDetails cannot be null");

        if (entity.getId() == null || entity.getId().isEmpty()) {
            String nextId = SequentialIdGenerator.getNextId(
                    repository.findAll(),
                    MagazineDetails::getId,
                    "mag/det-");
            entity.setId(nextId);
        }
        return repository.save(entity);
    }

    public Optional<MagazineDetails> findById(String id) {
        return repository.findById(id);
    }

    public List<MagazineDetails> findAll() {
        return repository.findAll();
    }

    public MagazineDetails update(MagazineDetails entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException("MagazineDetails and Id cannot be null");
        repository.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("MagazineDetails with Id " + entity.getId() + " does not exist"));
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}

