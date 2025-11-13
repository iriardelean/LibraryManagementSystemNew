package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.Member;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

@Repository
public class MemberRepository extends InFileRepository<Member, String> {

    public MemberRepository() {
        super("member.json", Member::getId, new TypeReference<List<Member>>() {});
    }
}