package com.example.librarymanagementsystemnew.repository;
import com.example.librarymanagementsystemnew.model.Author;
import com.example.librarymanagementsystemnew.model.BookAuthor;

import java.util.*;

public class AuthorRepository extends InMemoryRepository<Author, String> {

    public AuthorRepository() {
        super(Author::getId);
        String author1Id = UUID.randomUUID().toString();
        String book1Id = UUID.randomUUID().toString();
        String book2Id = UUID.randomUUID().toString();

        List<BookAuthor> author1Books = new ArrayList<>();
        author1Books.add(new BookAuthor(UUID.randomUUID().toString(), book1Id, author1Id));
        author1Books.add(new BookAuthor(UUID.randomUUID().toString(), book2Id, author1Id));

        Author author1 = new Author(author1Id, "J.R.R. Tolkien", author1Books);

        String author2Id = UUID.randomUUID().toString();
        String book3Id = UUID.randomUUID().toString();
        String book4Id = UUID.randomUUID().toString();

        List<BookAuthor> author2Books = new ArrayList<>();
        author2Books.add(new BookAuthor(UUID.randomUUID().toString(), book3Id, author2Id));
        author2Books.add(new BookAuthor(UUID.randomUUID().toString(), book4Id, author2Id));

        Author author2 = new Author(author2Id, "George Orwell", author2Books);

        String author3Id = UUID.randomUUID().toString();
        String book5Id = UUID.randomUUID().toString();

        List<BookAuthor> author3Books = new ArrayList<>();
        author3Books.add(new BookAuthor(UUID.randomUUID().toString(), book5Id, author3Id));

        Author author3 = new Author(author3Id, "Jane Austen", author3Books);

        save(author1);
        save(author2);
        save(author3);
    }
}
