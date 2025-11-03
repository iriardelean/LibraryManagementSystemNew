package com.example.librarymanagementsystemnew.model;

import java.util.List;

public class Author {
    private String Id;
    private String Name;
    private List<BookAuthor> books;

    // new properties
    private String biography;
    private Integer birthYear;

    public Author(String id, String name, List<BookAuthor> books) {
        Id = id;
        Name = name;
        this.books = books;
    }

    public Author(String id, String name, List<BookAuthor> books, String biography, Integer birthYear) {
        Id = id;
        Name = name;
        this.books = books;
        this.biography = biography;
        this.birthYear = birthYear;
    }

    public Author() {
        this.books = new java.util.ArrayList<>();
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
