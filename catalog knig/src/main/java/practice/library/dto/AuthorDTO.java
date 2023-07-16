package practice.library.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import practice.library.models.Author;
import practice.library.models.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {

    interface NewAuthor {
    }

    interface ExistAuthors {
    }

    @Null(groups = {NewAuthor.class}) //автогенерация в БД
    @NotNull(groups = {ExistAuthors.class, BookDTO.NewBook.class})
    @JsonView({BookDTO.AllInfo.class, BookDTO.NotAllInfo.class})
    private Long id;
    @NotNull(groups = {NewAuthor.class, ExistAuthors.class})
    @Null(groups = {BookDTO.NewBook.class})
    @JsonView({BookDTO.AllInfo.class, BookDTO.NotAllInfo.class})
    private String name;
    @NotNull(groups = {NewAuthor.class, ExistAuthors.class})
    @Null(groups = {BookDTO.NewBook.class})
    @JsonView({BookDTO.AllInfo.class})
    private List<BookDTO> books = new ArrayList<>();


    public AuthorDTO() {
        name = null;
    }

    public AuthorDTO(String name, List<Book> books) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public void addBook(BookDTO book) {
        this.books.add(book);
    }

    public static AuthorDTO fromAuthor(Author user) {
        AuthorDTO userDto = new AuthorDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        for (Book book : user.getBooks()) {
            userDto.addBook(BookDTO.fromBookWithoutAuthors(book));
        }

        return userDto;
    }


    public static Author fromAuthorDTO(AuthorDTO authorDTO) {
        Author author = new Author();

        author.setName(authorDTO.getName());


        return author;
    }
}
