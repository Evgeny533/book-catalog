package practice.library.repositories;

import org.hibernate.annotations.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.library.models.Book;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {
    @OrderBy(clause = "date_added")
    List<Book> findBookByTitleContainingIgnoreCase(String contain);

//    List<Book> findAllBooks ();

//    Book findById(Long studentId);
}
