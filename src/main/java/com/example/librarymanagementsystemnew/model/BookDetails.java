package com.example.librarymanagementsystemnew.model;

import java.util.ArrayList;
import java.util.List;

public class BookDetails extends Publication {
    private List<BookAuthor> bookAuthors;

    // new properties
    private String isbn;
    private Integer pageCount;

    public BookDetails(String id, String title) {
        super(title, id);
        this.bookAuthors = new ArrayList<>();
    }

    public BookDetails(String id, String title, String isbn, Integer pageCount) {
        super(title, id);
        this.bookAuthors = new ArrayList<>();
        this.isbn = isbn;
        this.pageCount = pageCount;
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
