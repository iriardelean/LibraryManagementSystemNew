package com.example.librarymanagementsystemnew.model;

public class MagazineDetails extends Publication {
    private String Publisher;

    public MagazineDetails(String id, String title, String Publisher) {
        super(id, title);
        this.Publisher = Publisher;
    }

    public MagazineDetails() {
        super(null, null);
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }
}
