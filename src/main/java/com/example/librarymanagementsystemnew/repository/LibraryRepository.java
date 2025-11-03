package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.Library;

public class LibraryRepository extends InMemoryRepository<Library, String> {

    public LibraryRepository() {
        super(Library::getId);
    }
}