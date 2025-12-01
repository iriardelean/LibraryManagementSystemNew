package com.example.librarymanagementsystemnew.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<ReadableItem> copies = new ArrayList<>();

    public Publication() {}

    public Publication(String title) {
        this.title = title;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<ReadableItem> getCopies() { return copies; }
    public void setCopies(List<ReadableItem> copies) { this.copies = copies; }
}