package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.ReadableItem;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

@Repository
public class ReadableItemRepository extends InFileRepository<ReadableItem, String> {

    public ReadableItemRepository() {
        super("readableitem.json", ReadableItem::getId, new TypeReference<List<ReadableItem>>() {
        });
    }
}