package com.example.librarymanagementsystemnew.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private List<Member> members;
    private List<ReadableItem> readableItems;

    public Library(Long id, String name, List<Member> members, List<ReadableItem> readableItems) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.readableItems = readableItems;
    }

    public Library() {
        this.members = new ArrayList<>();
        this.readableItems = new ArrayList<>();
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

    public List<Member> getMembers() {
        return this.members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<ReadableItem> getReadableItems() {
        return readableItems;
    }

    public void setReadableItems(List<ReadableItem> readableItems) {
        this.readableItems = readableItems;
    }

}
