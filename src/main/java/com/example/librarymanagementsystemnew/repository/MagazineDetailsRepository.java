package com.example.librarymanagementsystemnew.repository;

import com.example.librarymanagementsystemnew.model.MagazineDetails;

public class MagazineDetailsRepository extends InMemoryRepository<MagazineDetails, String>{

    public MagazineDetailsRepository() {
        super(MagazineDetails::getId);
    }
}
