package com.example.librarymanagementsystemnew.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotBlank(message = "Period is required")
    private String period;

    @ManyToMany(mappedBy = "authors")
    private List<BookDetails> books = new ArrayList<>();

    public Author() {}

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }
    public List<BookDetails> getBooks() { return books; }
    public void setBooks(List<BookDetails> books) { this.books = books; }
}