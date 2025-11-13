package com.example.librarymanagementsystemnew.model;

import java.util.List;

public class Author {
    private String id;
    private String name;
    private List<BookAuthor> books;

    // new properties
    private String biography;
    private Integer birthYear;

    public Author(String id, String name, List<BookAuthor> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Author(String id, String name, List<BookAuthor> books, String biography, Integer birthYear) {
        this.id = id;
        this.name = name;
        this.books = books;
        this.biography = biography;
        this.birthYear = birthYear;
    }

    public Author() {
        this.books = new java.util.ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }
}
