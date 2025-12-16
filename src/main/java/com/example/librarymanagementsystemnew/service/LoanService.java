package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.Loan;
import com.example.librarymanagementsystemnew.model.ReadableItem;
import com.example.librarymanagementsystemnew.model.ReadableItemStatus;
import com.example.librarymanagementsystemnew.repository.LoanRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository repository;

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Loan createLoan(Loan loan) {
        for (ReadableItem item : loan.getItems()) {
            if (item.getStatus() != ReadableItemStatus.AVAILABLE) {
                throw new IllegalStateException("Item " + item.getBarcode() + " is not available.");
            }

            item.setStatus(ReadableItemStatus.BORROWED);
        }
        return repository.save(loan);
    }

    public Optional<Loan> getLoanById(Long id) {
        return repository.findById(id);
    }

    public List<Loan> getAllLoans() {
        return repository.findAll();
    }

    public Loan updateLoan(Loan entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException("Loan and Id cannot be null");
        repository.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Loan with Id " + entity.getId() + " does not exist"));
        return repository.save(entity);
    }

    @Transactional
    public void deleteLoan(Long id) {
        Loan loan = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        for (ReadableItem item : loan.getItems()) {
            item.setStatus(ReadableItemStatus.AVAILABLE);
        }
        repository.deleteById(id);
    }

    public List<Loan> searchLoans(String memberName, LocalDate minDate, LocalDate maxDate, String sortField, String sortDir) {
        Specification<Loan> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (memberName != null && !memberName.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("member").get("name")), "%" + memberName.toLowerCase() + "%"));
            }
            if (minDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("date"), minDate));
            }
            if (maxDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("date"), maxDate));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();
        return repository.findAll(spec, sort);
    }
}