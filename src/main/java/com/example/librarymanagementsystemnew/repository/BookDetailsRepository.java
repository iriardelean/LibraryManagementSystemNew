package com.example.librarymanagementsystemnew.repository;

import com.example.librarymanagementsystemnew.model.BookDetails;

public class BookDetailsRepository extends InMemoryRepository<BookDetails, String>{

    public BookDetailsRepository() {
        super(BookDetails::getId);
    }
}
