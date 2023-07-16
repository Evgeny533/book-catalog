package practice.library.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import practice.library.models.Author;
import practice.library.models.Book;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDTO {
    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }

    public interface NewBook {
    }

    public interface ExistBook {
    }

    public interface AllInfo {
    }

    public interface NotAllInfo {
    }


    @Null(groups = {NewBook.class}) //автогенерация в БД
    @NotNull(groups = {ExistBook.class})
    @JsonView({AllInfo.class, BookDTO.NotAllInfo.class})
    private Long id;

    @NotNull(groups = {NewBook.class, ExistBook.class})
    @JsonView({AllInfo.class, NotAllInfo.class})
//    private String name;
    private String title;
    @NotNull(groups = {NewBook.class, ExistBook.class})
    @JsonView({AllInfo.class})
    private List<AuthorDTO> author = new ArrayList<>();

    @NotNull(groups = {NewBook.class, ExistBook.class})
    @JsonView({AllInfo.class, NotAllInfo.class})
    private Timestamp dateAdded;

    public BookDTO() {
    }

    public BookDTO(String title, List<AuthorDTO> author, Timestamp dateAdded) {
        this.title = title;
        this.author = author;
        this.dateAdded = dateAdded;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorDTO> getAuthor() {
        return author;
    }

    public void setAuthor(List<AuthorDTO> author) {
        this.author = author;
    }

    public void addAuthor(AuthorDTO author) {
        this.author.add(author);
    }

    public static BookDTO fromBook(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDateAdded(book.getDateAdded());
        for (Author author : book.getAuthor()) {
            bookDTO.addAuthor(AuthorDTO.fromAuthor(author));
        }

        return bookDTO;
    }

    public static BookDTO fromBookWithoutAuthors(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDateAdded(book.getDateAdded());
        return bookDTO;
    }


    public static Book fromBookDTOWithOutAuthors(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        if (Objects.nonNull(book.getDateAdded()))
            book.setDateAdded(book.getDateAdded());
        else
            book.setDateAdded(new Timestamp(System.currentTimeMillis()));
        return book;
    }
}
