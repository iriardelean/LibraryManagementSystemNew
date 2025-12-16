package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails,Long>, JpaSpecificationExecutor<BookDetails> {
}
