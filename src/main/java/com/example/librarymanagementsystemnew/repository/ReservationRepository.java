package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.Reservation;
import com.example.librarymanagementsystemnew.model.ReservationStatus;

import java.util.*;

public class ReservationRepository extends InMemoryRepository<Reservation, String> {

    public ReservationRepository() {
        super(Reservation::getId);

        String member1Id = UUID.randomUUID().toString();
        String member2Id = UUID.randomUUID().toString();
        String item1Id = UUID.randomUUID().toString();
        String item2Id = UUID.randomUUID().toString();
        String item3Id = UUID.randomUUID().toString();

        Reservation res1 = new Reservation(
                UUID.randomUUID().toString(),
                member1Id,
                item1Id,
                new Date()
        );

        Reservation res2 = new Reservation(
                UUID.randomUUID().toString(),
                member2Id,
                item2Id,
                new Date()
        );

        Reservation res3 = new Reservation(
                UUID.randomUUID().toString(),
                member1Id,
                item3Id,
                new Date()
        );
        res3.setStatus(ReservationStatus.COMPLETED);

        Reservation res4 = new Reservation(
                UUID.randomUUID().toString(),
                member2Id,
                item1Id,
                new Date()
        );
        res4.setStatus(ReservationStatus.CANCELLED);

        save(res1);
        save(res2);
        save(res3);
        save(res4);
    }
}