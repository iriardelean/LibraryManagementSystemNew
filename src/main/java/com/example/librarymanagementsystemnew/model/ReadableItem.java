package com.example.librarymanagementsystemnew.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class ReadableItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Barcode is required")
    private String barcode;

    @Enumerated(EnumType.STRING)
    private ReadableItemStatus status = ReadableItemStatus.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    @NotNull(message = "Publication (Book or Magazine) is required")
    private Publication publication;

    public ReadableItem() {}

    // Getters and Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }
    public ReadableItemStatus getStatus() { return status; }
    public void setStatus(ReadableItemStatus status) { this.status = status; }
    public Publication getPublication() { return publication; }
    public void setPublication(Publication publication) { this.publication = publication; }
}