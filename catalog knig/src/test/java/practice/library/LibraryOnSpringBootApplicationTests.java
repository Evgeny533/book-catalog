package practice.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.library.models.Author;
import practice.library.models.Book;
import practice.library.services.AuthorService;
import practice.library.services.BookService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryOnSpringBootApplicationTests {


    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Test
    @Transactional
    void booksExists() {
        List<Book> index = bookService.index(0, 10);
        assertEquals(true, index.size() > 0);
    }

    @Test
    @Transactional
    void authorsExists() {
        List<Author> authors = authorService.index();
        assertEquals(true, authors.size() > 0);
    }

}
