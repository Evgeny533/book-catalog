package practice.library.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.library.models.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    List<Author> findPersonByNameContainingIgnoreCase(String contain, Sort sort);


//    Optional<Author> findPersonByName (String username,  Sort sort );
//    Author findByAuthorId(Long courseId);


//    Page<Author> findAll(PageRequest name);
}
