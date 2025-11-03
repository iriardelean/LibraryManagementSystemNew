package com.example.librarymanagementsystemnew.repository;

import com.example.librarymanagementsystemnew.model.Publication;

public class PublicationRepository extends InMemoryRepository<Publication, String> {

    public PublicationRepository() {
        super(Publication::getId);
    }
}