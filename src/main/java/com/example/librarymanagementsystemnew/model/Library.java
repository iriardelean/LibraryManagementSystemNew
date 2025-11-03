package com.example.librarymanagementsystemnew.model;

import java.util.List;

public class Library {
    private String Id;
    private String Name;
    private List<Member> Members;
    private List<ReadableItem> ReadableItems;

    public Library(String id, String name, List<Member> members, List<ReadableItem> readableItems) {
        Id = id;
        Name = name;
        Members = members;
        ReadableItems = readableItems;
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

    public List<Member> getMembers() {
        return Members;
    }

    public void setMembers(List<Member> members) {
        Members = members;
    }

    public List<ReadableItem> getReadableItems() {
        return ReadableItems;
    }

    public void setReadableItems(List<ReadableItem> readableItems) {
        ReadableItems = readableItems;
    }

}
