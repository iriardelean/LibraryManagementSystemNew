package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.BookAuthor;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookAuthorRepository extends InFileRepository<BookAuthor, String> {

    public BookAuthorRepository() {
        super("bookauthor.json", BookAuthor::getId, new TypeReference<List<BookAuthor>>() {});
    }
}