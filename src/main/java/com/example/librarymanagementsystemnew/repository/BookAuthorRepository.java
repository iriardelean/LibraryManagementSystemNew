package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor,Long> {
}