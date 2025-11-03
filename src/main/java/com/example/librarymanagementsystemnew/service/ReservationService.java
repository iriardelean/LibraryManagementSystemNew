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

    public ReservationService() {
        this(new ReservationRepository());
    }

    public Reservation create(Reservation entity) {
        if (entity == null)
            throw new IllegalArgumentException("Reservation cannot be null");
        return repository.save(entity);
    }

    public Optional<Reservation> findById(String id) {
        return repository.findById(id);
    }

    public List<Reservation> findAll() {
        return repository.findAll();
    }

    public Reservation update(Reservation entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException("Reservation and Id cannot be null");
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}

