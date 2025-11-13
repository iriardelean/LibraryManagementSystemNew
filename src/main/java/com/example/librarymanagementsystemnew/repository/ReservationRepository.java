package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.Reservation;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

@Repository
public class ReservationRepository extends InFileRepository<Reservation, String> {

    public ReservationRepository() {
        super("reservation.json", Reservation::getId, new TypeReference<List<Reservation>>() {});
    }
}