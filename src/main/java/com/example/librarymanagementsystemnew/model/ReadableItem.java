package com.example.librarymanagementsystemnew.model;

public class ReadableItem {
    private String Id;
    private String publicationId;
    private String barcode;
    private ReadableItemsStatus readableItemsStatus;

    public ReadableItem(String id, String publicationId, String barcode) {
        Id = id;
        this.publicationId = publicationId;
        this.barcode = barcode;
        this.readableItemsStatus = ReadableItemsStatus.AVAILABLE;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public ReadableItemsStatus getStatus() {
        return readableItemsStatus;
    }

    public void setStatus(ReadableItemsStatus readableItemsStatus) {
        this.readableItemsStatus = readableItemsStatus;
    }


}
