package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanRepository extends JpaRepository<Loan,Long>, JpaSpecificationExecutor<Loan> {
}
