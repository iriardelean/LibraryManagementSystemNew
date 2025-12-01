package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.ReadableItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadableItemRepository extends JpaRepository<ReadableItem,Long> {

}