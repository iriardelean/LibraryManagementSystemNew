package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.BookDetails;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

@Repository
public class BookDetailsRepository extends InFileRepository<BookDetails, String>{

    public BookDetailsRepository() {
        super("bookdetails.json", BookDetails::getId, new TypeReference<List<BookDetails>>() {});
    }
}
