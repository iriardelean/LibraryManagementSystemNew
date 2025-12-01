package com.example.librarymanagementsystemnew.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ReadableItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String publicationId;
    private String barcode;
    private ReadableItemStatus status;

    public ReadableItem(Long id, String publicationId, String barcode) {
        this.id = id;
        this.publicationId = publicationId;
        this.barcode = barcode;
        this.status = ReadableItemStatus.AVAILABLE;
    }

    public ReadableItem() {
        this.status = ReadableItemStatus.AVAILABLE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public ReadableItemStatus getStatus() {
        return status;
    }

    public void setStatus(ReadableItemStatus readableItemsStatus) {
        this.status = readableItemsStatus;
    }


}
