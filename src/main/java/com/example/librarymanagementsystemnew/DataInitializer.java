package com.example.librarymanagementsystemnew;

import com.example.librarymanagementsystemnew.model.*;
import com.example.librarymanagementsystemnew.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookDetailsRepository bookRepository;
    private final MagazineDetailsRepository magazineRepository;
    private final MemberRepository memberRepository;
    private final LibraryRepository libraryRepository;
    private final ReadableItemRepository itemRepository;
    private final LoanRepository loanRepository;
    private final ReservationRepository reservationRepository;

    public DataInitializer(AuthorRepository authorRepository, BookDetailsRepository bookRepository,
                           MagazineDetailsRepository magazineRepository, MemberRepository memberRepository,
                           LibraryRepository libraryRepository, ReadableItemRepository itemRepository,
                           LoanRepository loanRepository, ReservationRepository reservationRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.magazineRepository = magazineRepository;
        this.memberRepository = memberRepository;
        this.libraryRepository = libraryRepository;
        this.itemRepository = itemRepository;
        this.loanRepository = loanRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) {
        // Prevent duplication on restart if data exists
        if (libraryRepository.count() > 0) return;

        System.out.println("--- INITIALIZING HARDCODED DATA ---");

        // --- 1. AUTHORS (from author.json) ---
        Author auth1 = createAuthor("J.K. Rowling", "Fantasy", "Contemporary");
        Author auth2 = createAuthor("Isaac Asimov", "Science Fiction", "20th century");
        Author auth3 = createAuthor("Jane Austen", "Romance", "19th century");
        Author auth4 = createAuthor("Frank Herbert", "Science Fiction", "20th century");
        Author auth5 = createAuthor("Agatha Christie", "Mystery", "20th century");
        Author auth6 = createAuthor("Stephen King", "Horror", "Contemporary");
        Author auth7 = createAuthor("Ursula K. Le Guin", "Sci-Fi/Fantasy", "20th century");
        Author auth8 = createAuthor("Haruki Murakami", "Magical Realism", "Contemporary");
        Author auth9 = createAuthor("Neil Gaiman", "Fantasy", "Contemporary");
        Author auth10 = createAuthor("Octavia E. Butler", "Science Fiction", "20th century");
        Author auth11 = createAuthor("Terry Pratchett", "Fantasy/Satire", "Contemporary");

        // --- 2. BOOKS (from bookdetails.json) ---
        // I am manually linking authors based on your bookauthor.json data
        BookDetails book1 = createBook("To Kill a Mockingbird", "978-0061120084", 281, auth3);
        BookDetails book2 = createBook("1984", "978-0451524935", 328, auth2);
        BookDetails book3 = createBook("The Hobbit", "978-0547928227", 300, auth1);
        BookDetails book4 = createBook("Murder on the Orient Express", "978-0062073495", 272, auth5);
        BookDetails book5 = createBook("Dune", "978-0441172719", 412, auth4);
        BookDetails book6 = createBook("Norwegian Wood", "978-0375704024", 296, auth8);
        BookDetails book7 = createBook("Pride and Prejudice", "978-0141439518", 432, auth3);
        BookDetails book8 = createBook("Foundation", "978-0553293357", 256, auth2);
        BookDetails book9 = createBook("Good Omens", "978-0060853983", 432, auth11);
        BookDetails book10 = createBook("A Wizard of Earthsea", "978-0547722023", 208, auth7);
        BookDetails book11 = createBook("American Gods", "978-0380789030", 544, auth9);
        BookDetails book12 = createBook("The Shining", "978-0385121675", 447, auth6);

        // --- 3. MAGAZINES (from magazinedetails.json) ---
        MagazineDetails mag1 = createMagazine("National Geographic", "National Geographic Partners");
        MagazineDetails mag2 = createMagazine("Time", "Time USA, LLC");
        MagazineDetails mag3 = createMagazine("Scientific American", "Springer Nature");
        MagazineDetails mag4 = createMagazine("The New Yorker", "Condé Nast");
        MagazineDetails mag5 = createMagazine("Wired", "Condé Nast");
        MagazineDetails mag6 = createMagazine("Nature", "Springer Nature");
        MagazineDetails mag7 = createMagazine("The Economist", "The Economist Group");
        MagazineDetails mag8 = createMagazine("Popular Science", "Bonnier Corporation");
        MagazineDetails mag9 = createMagazine("Rolling Stone", "Penske Media Corporation");
        MagazineDetails mag10 = createMagazine("Vogue", "Condé Nast");
        MagazineDetails mag11 = createMagazine("Forbes", "Forbes Media");
        MagazineDetails mag12 = createMagazine("Smithsonian", "Smithsonian Institution");

        // --- 4. LIBRARIES (from library.json) ---
        Library lib1 = createLibrary("Central Library");
        Library lib2 = createLibrary("Community Branch");
        Library lib3 = createLibrary("Eastside Branch");
        Library lib4 = createLibrary("Westside Branch");
        Library lib5 = createLibrary("North Branch");
        Library lib6 = createLibrary("South Branch");
        Library lib7 = createLibrary("Hilltop Library");
        Library lib8 = createLibrary("Riverbend Library");
        Library lib9 = createLibrary("Digital Archive");
        Library lib10 = createLibrary("Research Annex");
        Library lib11 = createLibrary("Historical Society");

        // --- 5. MEMBERS (from member.json) ---
        Member memb1 = createMember("test1", "test1@test.com", LocalDate.of(2025, 11, 28), lib1);
        Member memb2 = createMember("Charlie Day", "charlie@example.com", LocalDate.of(2025, 1, 20), lib2);
        Member memb3 = createMember("Dana Scully", "dana@example.com", LocalDate.of(2025, 2, 10), lib1);
        Member memb4 = createMember("Evan Wright", "evan@example.com", LocalDate.of(2025, 3, 5), lib3);
        Member memb5 = createMember("Fiona Glenanne", "fiona@example.com", LocalDate.of(2025, 4, 15), lib2);
        Member memb6 = createMember("George Kirk", "george@example.com", LocalDate.of(2025, 5, 22), lib1);
        Member memb7 = createMember("Helen Magnus", "helen@example.com", LocalDate.of(2025, 6, 30), lib4);
        Member memb8 = createMember("Ian Malcolm", "ian@example.com", LocalDate.of(2025, 7, 7), lib1);
        Member memb9 = createMember("Julia Child", "julia@example.com", LocalDate.of(2025, 8, 14), lib5);
        Member memb10 = createMember("Kevin Flynn", "kevin@example.com", LocalDate.of(2025, 9, 19), lib9);
        Member memb11 = createMember("Laura Roslin", "laura@example.com", LocalDate.of(2025, 10, 25), lib1);

        // --- 6. READABLE ITEMS (from readableitem.json) ---
        ReadableItem item1 = createItem("BC1001", ReadableItemStatus.AVAILABLE, book1);
        ReadableItem item2 = createItem("BC1002", ReadableItemStatus.AVAILABLE, book2);
        ReadableItem item3 = createItem("BC2001", ReadableItemStatus.AVAILABLE, mag1);
        ReadableItem item4 = createItem("BC1003", ReadableItemStatus.AVAILABLE, book4);
        ReadableItem item5 = createItem("BC2002", ReadableItemStatus.AVAILABLE, mag3);
        ReadableItem item6 = createItem("BC1004", ReadableItemStatus.AVAILABLE, book5);
        ReadableItem item7 = createItem("BC1005", ReadableItemStatus.AVAILABLE, book6);
        ReadableItem item8 = createItem("BC2003", ReadableItemStatus.AVAILABLE, mag2);
        ReadableItem item9 = createItem("BC1006", ReadableItemStatus.AVAILABLE, book7);
        ReadableItem item10 = createItem("BC1007", ReadableItemStatus.AVAILABLE, book8);
        ReadableItem item11 = createItem("BC2004", ReadableItemStatus.AVAILABLE, mag4);
        ReadableItem item12 = createItem("BC1008", ReadableItemStatus.AVAILABLE, book9);
        ReadableItem item13 = createItem("BC1009", ReadableItemStatus.AVAILABLE, book10);

        // --- 7. RESERVATIONS (from reservation.json) ---
        createReservation(memb1, item2, LocalDate.parse("2025-10-01"), ReservationStatus.ACTIVE);
        createReservation(memb2, item1, LocalDate.parse("2025-11-01"), ReservationStatus.ACTIVE);
        createReservation(memb1, item4, LocalDate.parse("2025-11-02"), ReservationStatus.ACTIVE);
        createReservation(memb2, item7, LocalDate.parse("2025-11-03"), ReservationStatus.ACTIVE);
        createReservation(memb3, item9, LocalDate.parse("2025-11-04"), ReservationStatus.ACTIVE);
        createReservation(memb4, item10, LocalDate.parse("2025-11-05"), ReservationStatus.ACTIVE);
        createReservation(memb5, item12, LocalDate.parse("2025-11-06"), ReservationStatus.ACTIVE);
        createReservation(memb6, item13, LocalDate.parse("2025-11-07"), ReservationStatus.ACTIVE);
        createReservation(memb7, item3, LocalDate.parse("2025-11-08"), ReservationStatus.ACTIVE);
        createReservation(memb8, item5, LocalDate.parse("2025-11-09"), ReservationStatus.ACTIVE);
        createReservation(memb9, item8, LocalDate.parse("2025-11-10"), ReservationStatus.ACTIVE);

        // --- 8. LOANS (from loan.json) ---
        createLoan(memb2, LocalDate.parse("2025-09-15"), List.of(item1));
        createLoan(memb1, LocalDate.parse("2025-11-10"), List.of(item3));
        createLoan(memb1, LocalDate.parse("2025-11-11"), List.of(item2));
        createLoan(memb1, LocalDate.parse("2025-11-12"), List.of(item4));
        createLoan(memb2, LocalDate.parse("2025-11-13"), List.of(item5));
        createLoan(memb2, LocalDate.parse("2025-11-14"), List.of(item6));
        createLoan(memb3, LocalDate.parse("2025-11-15"), List.of(item7));
        createLoan(memb4, LocalDate.parse("2025-11-16"), List.of(item8));
        createLoan(memb5, LocalDate.parse("2025-11-17"), List.of(item9));
        createLoan(memb6, LocalDate.parse("2025-11-18"), List.of(item10));
        createLoan(memb7, LocalDate.parse("2025-11-19"), List.of(item11));

        System.out.println("--- DATA INITIALIZATION COMPLETE ---");
    }

    // Helper methods to keep code clean and repetitive tasks simple
    private Author createAuthor(String name, String genre, String period) {
        Author a = new Author();
        a.setName(name);
        a.setGenre(genre);
        a.setPeriod(period);
        return authorRepository.save(a);
    }

    private BookDetails createBook(String title, String isbn, int pages, Author author) {
        BookDetails b = new BookDetails();
        b.setTitle(title);
        b.setIsbn(isbn);
        b.setPageCount(pages);
        if (author != null) b.getAuthors().add(author);
        return bookRepository.save(b);
    }

    private MagazineDetails createMagazine(String title, String publisher) {
        MagazineDetails m = new MagazineDetails();
        m.setTitle(title);
        m.setPublisher(publisher);
        return magazineRepository.save(m);
    }

    private Library createLibrary(String name) {
        Library l = new Library();
        l.setName(name);
        return libraryRepository.save(l);
    }

    private Member createMember(String name, String email, LocalDate joined, Library lib) {
        Member m = new Member();
        m.setName(name);
        m.setEmail(email);
        m.setMembershipDate(joined);
        m.setLibrary(lib);
        return memberRepository.save(m);
    }

    private ReadableItem createItem(String barcode, ReadableItemStatus status, Publication pub) {
        ReadableItem item = new ReadableItem();
        item.setBarcode(barcode);
        item.setStatus(status);
        item.setPublication(pub);
        return itemRepository.save(item);
    }

    private Reservation createReservation(Member member, ReadableItem item, LocalDate date, ReservationStatus status) {
        Reservation r = new Reservation();
        r.setMember(member);
        r.setReadableItem(item);
        r.setDate(date);
        r.setStatus(status);
        return reservationRepository.save(r);
    }

    private Loan createLoan(Member member, LocalDate date, List<ReadableItem> items) {
        Loan l = new Loan();
        l.setMember(member);
        l.setDate(date);
        l.setItems(new ArrayList<>(items));
        return loanRepository.save(l);
    }
}