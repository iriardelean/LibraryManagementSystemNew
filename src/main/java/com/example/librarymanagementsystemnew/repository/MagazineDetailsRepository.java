package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.MagazineDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineDetailsRepository extends JpaRepository<MagazineDetails, Long> {
}
