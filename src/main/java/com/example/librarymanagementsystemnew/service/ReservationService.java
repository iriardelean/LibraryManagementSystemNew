package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.ReadableItem;
import com.example.librarymanagementsystemnew.model.ReadableItemStatus;
import com.example.librarymanagementsystemnew.model.Reservation;
import com.example.librarymanagementsystemnew.model.ReservationStatus;
import com.example.librarymanagementsystemnew.repository.ReservationRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Reservation createReservation(Reservation entity) {
        ReadableItem item = entity.getReadableItem();

        if (item == null) {
            throw new IllegalArgumentException("Reservation must have a valid item.");
        }
        // Logic: Can only reserve if it is available (or strictly speaking, you might reserve borrowed items,
        // but based on your previous logic, we enforce availability check here).
        if (item.getStatus() != ReadableItemStatus.AVAILABLE) {
            throw new IllegalStateException("Item " + item.getBarcode() + " is not available (Status: " + item.getStatus() + ")");
        }

        item.setStatus(ReadableItemStatus.RESERVED);

        entity.setStatus(ReservationStatus.ACTIVE);
        return repository.save(entity);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return repository.findById(id);
    }

    public List<Reservation> getAllReservation() {
        return repository.findAll();
    }

    public Reservation updateReservation(Reservation entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException("Reservation and Id cannot be null");
        repository.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Reservation with Id " + entity.getId() + " does not exist"));
        return repository.save(entity);
    }

    @Transactional
    public void deleteReservation(Long id) {
        Reservation reservation = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation with Id " + id + " does not exist"));

        ReadableItem item = reservation.getReadableItem();

        // If reservation is deleted/cancelled, free up the item
        if (item != null && item.getStatus() == ReadableItemStatus.RESERVED) {
            item.setStatus(ReadableItemStatus.AVAILABLE);
        }

        repository.deleteById(id);
    }

    public List<Reservation> searchReservations(String memberName, String itemTitle, LocalDate minDate, LocalDate maxDate, ReservationStatus status, String sortField, String sortDir) {
        Specification<Reservation> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (memberName != null && !memberName.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("member").get("name")), "%" + memberName.toLowerCase() + "%"));
            }
            if (itemTitle != null && !itemTitle.isEmpty()) {
                // Join Reservation -> ReadableItem -> Publication -> Title
                predicates.add(cb.like(cb.lower(root.get("readableItem").get("publication").get("title")), "%" + itemTitle.toLowerCase() + "%"));
            }
            if (minDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("date"), minDate));
            }
            if (maxDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("date"), maxDate));
            }
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();
        return repository.findAll(spec, sort);
    }
}