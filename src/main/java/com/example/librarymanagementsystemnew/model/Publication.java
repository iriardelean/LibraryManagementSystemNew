package com.example.librarymanagementsystemnew.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Publication {
    private String Id;
    private String Title;
    private List<ReadableItem> copies;

    public Publication(String Id, String Title) {
        this.Title = Title;
        this.copies = new ArrayList<>();
        this.Id = Id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public List<ReadableItem> getCopies() {
        return copies;
    }

    public void setCopies(List<ReadableItem> copies) {
        this.copies = copies;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

}
