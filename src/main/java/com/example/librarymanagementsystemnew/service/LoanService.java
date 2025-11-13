package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.Loan;
import com.example.librarymanagementsystemnew.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository repository;

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    public Loan create(Loan entity) {
        if (entity == null)
            throw new IllegalArgumentException("Loan cannot be null");

        if (entity.getId() == null || entity.getId().isEmpty()) {
            String nextId = SequentialIdGenerator.getNextId(
                    repository.findAll(),
                    Loan::getId,
                    "loan-");
            entity.setId(nextId);
        }
        return repository.save(entity);
    }

    public Optional<Loan> findById(String id) {
        return repository.findById(id);
    }

    public List<Loan> findAll() {
        return repository.findAll();
    }

    public Loan update(Loan entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException("Loan and Id cannot be null");
        repository.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Loan with Id " + entity.getId() + " does not exist"));
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}

