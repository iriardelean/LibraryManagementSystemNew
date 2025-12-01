package com.example.librarymanagementsystemnew.repository;

import com.example.librarymanagementsystemnew.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

}