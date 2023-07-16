package practice.library.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import practice.library.dto.AuthorDTO;
import practice.library.dto.BookDTO;
import practice.library.dto.ResponseDto;
import practice.library.models.Author;
import practice.library.models.Book;
import practice.library.services.AuthorService;
import practice.library.services.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/api/v1/books")
public class BooksController {
    private final BookService bookService;

    private final AuthorService authorService;


    @Autowired
    public BooksController(BookService bookService, AuthorService authorService
    ) {
        this.bookService = bookService;
        this.authorService = authorService;


    }

    @JsonView(BookDTO.NotAllInfo.class)
    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseDto> getAll(@RequestParam(value = "page", required = true) Integer page,
                                              @RequestParam(value = "booksPerPage", required = true) Integer booksPerPage) {
        List<Book> books = bookService.index(page, booksPerPage);
        ResponseDto responseDto = new ResponseDto();
        if (Objects.nonNull(books) && !books.isEmpty()) {
            List<BookDTO> result = new ArrayList<>();
            for (Book book : books) {
                result.add(BookDTO.fromBook(book));
            }
            responseDto.setResponse(1, "SUCCESS", "Книги найдены", result);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            responseDto.setResponse(-1, "FAIL", "Книги не найдены", null);
            return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
        }
    }

    @JsonView(BookDTO.NotAllInfo.class)
    @GetMapping(value = "/getBookByTitle")
    public ResponseEntity<ResponseDto> getBookByTitle(@RequestParam(value = "title", required = false) String title) {
        List<Book> books = bookService.search(title);
        ResponseDto responseDto = new ResponseDto();
        if (Objects.nonNull(books) && !books.isEmpty()) {
            List<BookDTO> result = new ArrayList<>();
            for (Book book : books) {
                result.add(BookDTO.fromBook(book));
            }
            responseDto.setResponse(1, "SUCCESS", "Книги найдены", result);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            responseDto.setResponse(-1, "FAIL", "Книги не найдены", null);
            return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
        }
    }

    @JsonView(BookDTO.AllInfo.class)
    @GetMapping(value = "/getBookById")
    public ResponseEntity<ResponseDto> getBookById(@RequestParam(value = "id", required = false) Integer id) {
        Book show = bookService.show(id);
        ResponseDto responseDto = new ResponseDto();
        if (Objects.nonNull(show)) {
            BookDTO bookDTO = BookDTO.fromBook(show);
            responseDto.setResponse(1, "SUCCESS", "Книга найдена", bookDTO);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            responseDto.setResponse(-1, "FAIL", "Книга не найдена", null);
            return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
        }
    }

    @Transactional
    @PostMapping(value = "/addBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> addBook(@RequestBody BookDTO bookDTO) {


        Book book = BookDTO.fromBookDTOWithOutAuthors(bookDTO);

        for (AuthorDTO authorDTO : bookDTO.getAuthor()) {
            Author show = authorService.show(authorDTO.getId());
            if (Objects.nonNull(show)) {
                book.addAuthor(show);
            }
        }

        bookService.save(book);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse(1, "SUCCESS", "Книга сохранена", book.getId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}