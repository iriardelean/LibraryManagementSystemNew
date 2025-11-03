package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.ReadableItem;

public class ReadableItemRepository extends InMemoryRepository<ReadableItem, String> {

    public ReadableItemRepository() {
        super(ReadableItem::getId);
    }
}