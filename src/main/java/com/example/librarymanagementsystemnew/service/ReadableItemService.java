package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.ReadableItem;
import com.example.librarymanagementsystemnew.model.ReadableItemStatus;
import com.example.librarymanagementsystemnew.repository.ReadableItemRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReadableItemService {

    private final ReadableItemRepository repository;

    public ReadableItemService(ReadableItemRepository repository) {
        this.repository = repository;
    }

    public ReadableItem createReadableItem(ReadableItem entity) {
        return repository.save(entity);
    }

    public Optional<ReadableItem> getReadableItemById(Long id) {
        return repository.findById(id);
    }

    public List<ReadableItem> getAvailableReadableItems() {
        return repository.findByStatus(ReadableItemStatus.AVAILABLE);
    }

    public List<ReadableItem> getAllReadableItem() {
        return repository.findAll();
    }

    public ReadableItem updateReadableItem(ReadableItem entity) {
        return repository.save(entity);
    }

    public void deleteReadableItem(Long id) {
        repository.deleteById(id);
    }

    public List<ReadableItem> searchReadableItems(String barcode, ReadableItemStatus status, String publicationTitle, String sortField, String sortDir) {
        Specification<ReadableItem> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (barcode != null && !barcode.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("barcode")), "%" + barcode.toLowerCase() + "%"));
            }
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            if (publicationTitle != null && !publicationTitle.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("publication").get("title")), "%" + publicationTitle.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();
        return repository.findAll(spec, sort);
    }
}