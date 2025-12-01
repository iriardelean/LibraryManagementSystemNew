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

    public MagazineDetails createMagazine(MagazineDetails entity) {
        return repository.save(entity);
    }

    public Optional<MagazineDetails> getMagazineById(Long id) {
        return repository.findById(id);
    }

    public List<MagazineDetails> getAllMagazines() {
        return repository.findAll();
    }

    public MagazineDetails updateMagazine(MagazineDetails entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException("MagazineDetails and Id cannot be null");
        repository.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("MagazineDetails with Id " + entity.getId() + " does not exist"));
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

