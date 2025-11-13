package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.Author;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository extends InFileRepository<Author, String> {

    public AuthorRepository() {
        super("author.json", Author::getId, new TypeReference<List<Author>>() {});
    }
}