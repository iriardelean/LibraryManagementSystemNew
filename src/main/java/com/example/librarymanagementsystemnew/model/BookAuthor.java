package com.example.librarymanagementsystemnew.model;


public class BookAuthor {
    private String Id;
    private String bookId;
    private String authorId;

    public BookAuthor(String id, String bookId, String authorId) {
        Id = id;
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

}
