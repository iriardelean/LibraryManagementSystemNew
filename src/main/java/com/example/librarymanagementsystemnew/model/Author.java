package com.example.librarymanagementsystemnew.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    private List<BookAuthor> books;

    // new properties
    private String genre;
    private String period;

    public Author(Long id, String name, List<BookAuthor> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Author(Long id, String name, List<BookAuthor> books, String genre, String period) {
        this.id = id;
        this.name = name;
        this.books = books;
        this.genre = genre;
        this.period = period;
    }

    public Author() {
        this.books = new java.util.ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookAuthor> getBooks() {
        return books;
    }

    public void setBooks(List<BookAuthor> books) {
        this.books = books;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
