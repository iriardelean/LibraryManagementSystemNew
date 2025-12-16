package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.Member;
import com.example.librarymanagementsystemnew.repository.MemberRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Member createMember(Member entity) {
        return repository.save(entity);
    }

    public Optional<Member> getMemberById(Long id) {
        return repository.findById(id);
    }

    public Member updateMember(Member entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException("Member and Id cannot be null");
        repository.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Member with Id " + entity.getId() + " does not exist"));
        return repository.save(entity);
    }

    public void deleteMember(Long id) {
        repository.deleteById(id);
    }

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public List<Member> searchMembers(String name, String email, LocalDate minDate, LocalDate maxDate, String sortField, String sortDir) {
        Specification<Member> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter by Name (contains, ignore case)
            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            // Filter by Email (contains, ignore case)
            if (email != null && !email.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
            }
            if (minDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("membershipDate"), minDate));
            }
            if (maxDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("membershipDate"), maxDate));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();

        return repository.findAll(spec, sort);
    }
}