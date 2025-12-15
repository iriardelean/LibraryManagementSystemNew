package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.ReadableItem;
import com.example.librarymanagementsystemnew.model.ReadableItemStatus;
import com.example.librarymanagementsystemnew.model.Reservation;
import com.example.librarymanagementsystemnew.model.ReservationStatus;
import com.example.librarymanagementsystemnew.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

        if (item != null && item.getStatus() == ReadableItemStatus.RESERVED) {
            item.setStatus(ReadableItemStatus.AVAILABLE);
        }

        repository.deleteById(id);
    }
}

