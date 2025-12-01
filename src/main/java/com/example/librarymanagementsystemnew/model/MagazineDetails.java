package com.example.librarymanagementsystemnew.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class MagazineDetails extends Publication {

    @NotBlank(message = "Publisher is required")
    private String publisher;

    public MagazineDetails() {}

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
}