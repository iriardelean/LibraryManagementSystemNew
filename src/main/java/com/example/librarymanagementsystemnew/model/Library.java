package com.example.librarymanagementsystemnew.model;

import java.util.List;

public class Library {
    private String id;
    private String name;
    private List<Member> members;
    private List<ReadableItem> readableItems;

    public Library(String id, String name, List<Member> members, List<ReadableItem> readableItems) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.readableItems = readableItems;
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
