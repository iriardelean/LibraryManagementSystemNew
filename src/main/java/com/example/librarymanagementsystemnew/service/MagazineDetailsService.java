package com.example.librarymanagementsystemnew.service;

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

    public MagazineDetailsService() {
        this(new MagazineDetailsRepository());
    }

    public MagazineDetails create(MagazineDetails entity) {
        if (entity == null)
            throw new IllegalArgumentException("MagazineDetails cannot be null");
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
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}

