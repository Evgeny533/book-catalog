package practice.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.library.models.Book;
import practice.library.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BooksRepository booksRepository;

    private final AuthorService authorService;

    @Autowired
    public BookService(BooksRepository booksRepository, AuthorService authorService) {
        this.booksRepository = booksRepository;
        this.authorService = authorService;
    }

    public List<Book> index(Integer page, Integer booksPerPage) {
        Pageable pageable = PageRequest.of(page, booksPerPage, Sort.by("dateAdded").descending());
        return booksRepository.findAll(pageable).getContent();
    }


    public Book show(long id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }


    public List<Book> search(String contain) {
        return booksRepository.findBookByTitleContainingIgnoreCase(contain);
    }
}
