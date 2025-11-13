package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.Library;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

@Repository
public class LibraryRepository extends InFileRepository<Library, String> {

    public LibraryRepository() {
        super("library.json", Library::getId, new TypeReference<List<Library>>() {});
    }
}