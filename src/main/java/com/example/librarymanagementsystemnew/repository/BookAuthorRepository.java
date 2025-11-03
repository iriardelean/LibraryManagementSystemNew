package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.BookAuthor;

public class BookAuthorRepository extends InMemoryRepository<BookAuthor, String> {

    public BookAuthorRepository() {
        super(BookAuthor::getId);
    }
}