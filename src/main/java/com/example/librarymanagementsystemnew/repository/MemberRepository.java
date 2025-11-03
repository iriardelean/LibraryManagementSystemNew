package com.example.librarymanagementsystemnew.repository;

import com.example.librarymanagementsystemnew.model.Member;

public class MemberRepository extends InMemoryRepository<Member, String> {

    public MemberRepository() {
        super(Member::getId);
    }
}