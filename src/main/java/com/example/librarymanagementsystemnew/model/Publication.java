package com.example.librarymanagementsystemnew.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Publication {
    private String id;
    private String title;
    private List<ReadableItem> copies;

    public Publication(String id, String Title) {
        this.title = Title;
        this.copies = new ArrayList<>();
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ReadableItem> getCopies() {
        return copies;
    }

    public void setCopies(List<ReadableItem> copies) {
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
