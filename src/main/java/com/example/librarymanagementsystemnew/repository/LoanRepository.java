package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.Loan;

public class LoanRepository extends InMemoryRepository<Loan, String> {

    public LoanRepository() {
        super(Loan::getId);
    }
}