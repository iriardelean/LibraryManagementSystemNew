package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.Reservation;
import com.example.librarymanagementsystemnew.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public Reservation createReservation(Reservation entity) {
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

    public void deleteReservation(Long id) {
        repository.deleteById(id);
    }
}

