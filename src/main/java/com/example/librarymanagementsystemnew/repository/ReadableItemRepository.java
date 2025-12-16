package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.ReadableItem;
import com.example.librarymanagementsystemnew.model.ReadableItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadableItemRepository extends JpaRepository<ReadableItem,Long>, JpaSpecificationExecutor<ReadableItem> {
    List<ReadableItem> findByStatus(ReadableItemStatus status);
}