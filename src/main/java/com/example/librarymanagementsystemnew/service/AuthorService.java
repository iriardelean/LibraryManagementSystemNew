package com.example.librarymanagementsystemnew.service;

import com.example.librarymanagementsystemnew.model.*;
import com.example.librarymanagementsystemnew.repository.AuthorRepository;
import com.example.librarymanagementsystemnew.repository.BookDetailsRepository;
import com.example.librarymanagementsystemnew.repository.LoanRepository;
import com.example.librarymanagementsystemnew.repository.ReservationRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookDetailsRepository bookDetailsRepository;
    private final ReservationRepository reservationRepository;
    private final LoanRepository loanRepository;

    public AuthorService(AuthorRepository authorRepository, BookDetailsRepository bookDetailsRepository, ReservationRepository reservationRepository, LoanRepository loanRepository) {
        this.authorRepository = authorRepository;
        this.bookDetailsRepository = bookDetailsRepository;
        this.reservationRepository = reservationRepository;
        this.loanRepository = loanRepository;
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author updateAuthor(Author author) {
        if (author == null || author.getId() == null)
            throw new IllegalArgumentException("Author and Id cannot be null");
        return authorRepository.save(author);
    }

    @Transactional
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + id));

        List<BookDetails> books = new ArrayList<>(author.getBooks());

        for (BookDetails book : books) {
            book.getAuthors().remove(author);

            if (book.getAuthors().isEmpty()) {

                for (ReadableItem item : book.getCopies()) {

                    List<Reservation> reservations = reservationRepository.findAll((root, query, cb) ->
                            cb.equal(root.get("readableItem"), item));
                    reservationRepository.deleteAll(reservations);

                    List<Loan> loans = loanRepository.findAll((root, query, cb) ->
                            cb.isMember(item, root.get("items")));

                    for (Loan loan : loans) {
                        loan.getItems().remove(item);
                        if (loan.getItems().isEmpty()) {
                            loanRepository.delete(loan);
                        } else {
                            loanRepository.save(loan);
                        }
                    }
                }

                bookDetailsRepository.delete(book);
            } else {
                bookDetailsRepository.save(book);
            }
        }

        authorRepository.delete(author);
    }

    public List<Author> searchAuthors(String name, String genre, String period, String sortField, String sortDir) {
        Specification<Author> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (genre != null && !genre.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("genre")), "%" + genre.toLowerCase() + "%"));
            }
            if (period != null && !period.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("period")), "%" + period.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();

        return authorRepository.findAll(spec, sort);
    }
}