package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.Author;

import java.util.function.Function;
public class AuthorRepository extends InMemoryRepository<Author, String> {

    public AuthorRepository() {
        super(Author::getId);
    }
}

