package com.example.librarymanagementsystemnew.model;

public class ReadableItem {
    private String id;
    private String publicationId;
    private String barcode;
    private ReadableItemStatus status;

    public ReadableItem(String id, String publicationId, String barcode) {
        this.id = id;
        this.publicationId = publicationId;
        this.barcode = barcode;
        this.status = ReadableItemStatus.AVAILABLE;
    }

    public ReadableItem() {
        this.status = ReadableItemStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
