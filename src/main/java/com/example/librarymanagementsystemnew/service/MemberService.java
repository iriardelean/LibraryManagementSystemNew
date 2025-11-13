package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.Author;
import com.example.librarymanagementsystemnew.model.Member;
import com.example.librarymanagementsystemnew.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Member create(Member entity) {
        if (entity == null)
            throw new IllegalArgumentException("Member cannot be null");

        if (entity.getId() == null || entity.getId().isEmpty()) {
            String nextId = SequentialIdGenerator.getNextId(
                    repository.findAll(),
                    Member::getId,
                    "memb-");
            entity.setId(nextId);
        }
        return repository.save(entity);
    }

    public Optional<Member> findById(String id) {
        return repository.findById(id);
    }

    public List<Member> findAll() {
        return repository.findAll();
    }

    public Member update(Member entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException("Member and Id cannot be null");
        repository.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Member with Id " + entity.getId() + " does not exist"));
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}

