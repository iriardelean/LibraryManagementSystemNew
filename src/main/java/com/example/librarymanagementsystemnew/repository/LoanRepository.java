package com.example.librarymanagementsystemnew.repository;

import com.example.librarymanagementsystemnew.model.Loan;
import com.example.librarymanagementsystemnew.model.ReadableItem;
import com.example.librarymanagementsystemnew.model.Reservation;

import java.util.*;
import java.time.LocalDate;


public class LoanRepository extends InMemoryRepository<Loan, String> {

    public LoanRepository() {
        super(Loan::getId);

        String member1Id = UUID.randomUUID().toString();
        String item1Id = UUID.randomUUID().toString();
        String item2Id = UUID.randomUUID().toString();

        List<ReadableItem> items1 = new ArrayList<>();
        items1.add(new ReadableItem(item1Id, "P1", "BC001"));
        items1.add(new ReadableItem(item2Id, "P2", "BC002"));

        List<Reservation> reservations1 = new ArrayList<>();
        reservations1.add(new Reservation(UUID.randomUUID().toString(), member1Id, item1Id, new LocalDate()));
        reservations1.add(new Reservation(UUID.randomUUID().toString(), member1Id, item2Id, new LocalDate()));

        Loan loan1 = new Loan(UUID.randomUUID().toString(), member1Id, new Date(), reservations1, items1);

        String member2Id = UUID.randomUUID().toString();
        String item3Id = UUID.randomUUID().toString();

        List<ReadableItem> items2 = new ArrayList<>();
        items2.add(new ReadableItem(item3Id, "P3", "BC003"));

        List<Reservation> reservations2 = new ArrayList<>();
        reservations2.add(new Reservation(UUID.randomUUID().toString(), member2Id, item3Id, new LocalDate()));

        Loan loan2 = new Loan(UUID.randomUUID().toString(), member2Id, new Date(), reservations2, items2);

        String member3Id = UUID.randomUUID().toString();
        String item4Id = UUID.randomUUID().toString();
        String item5Id = UUID.randomUUID().toString();

        List<ReadableItem> items3 = new ArrayList<>();
        items3.add(new ReadableItem(item4Id, "P4", "BC004"));
        items3.add(new ReadableItem(item5Id, "P5", "BC005"));

        List<Reservation> reservations3 = new ArrayList<>();
        reservations3.add(new Reservation(UUID.randomUUID().toString(), member3Id, item4Id, new LocalDate()));
        reservations3.add(new Reservation(UUID.randomUUID().toString(), member3Id, item5Id, new LocalDate()));

        Loan loan3 = new Loan(UUID.randomUUID().toString(), member3Id, new Date(), reservations3, items3);

        save(loan1);
        save(loan2);
        save(loan3);
    }
}
