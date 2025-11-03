package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.Reservation;

public class ReservationRepository extends InMemoryRepository<Reservation, String> {

    public ReservationRepository() {
        super(Reservation::getId);
    }
}