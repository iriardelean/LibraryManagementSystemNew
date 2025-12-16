package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.MagazineDetails;
import com.example.librarymanagementsystemnew.repository.MagazineDetailsRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<MagazineDetails> searchMagazines(String title, String publisher, String sortField, String sortDir) {
        Specification<MagazineDetails> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (publisher != null && !publisher.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("publisher")), "%" + publisher.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();
        return repository.findAll(spec, sort);
    }
}