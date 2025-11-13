package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.Loan;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.type.TypeReference;


@Repository
public class LoanRepository extends InFileRepository<Loan, String> {

    public LoanRepository() {
        super("loan.json", Loan::getId, new TypeReference<java.util.List<Loan>>() {});
    }
}
