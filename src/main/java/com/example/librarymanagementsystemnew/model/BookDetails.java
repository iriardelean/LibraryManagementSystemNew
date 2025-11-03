package com.example.librarymanagementsystemnew.model;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class BookDetails extends Publication {
    private List<BookAuthor> bookAuthors;

    // new properties
    private String isbn;
    private Integer pageCount;

    public BookDetails(String id, String title) {
        super(id, title);
        this.bookAuthors = new ArrayList<>();
    }

    public BookDetails(String id, String title, String isbn, Integer pageCount) {
        super(id, title);
        this.bookAuthors = new ArrayList<>();
        this.isbn = isbn;
        this.pageCount = pageCount;
    }

    public BookDetails() {
        super(null, null);
        this.bookAuthors = new ArrayList<>();
    }

    public List<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
