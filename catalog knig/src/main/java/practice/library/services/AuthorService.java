package practice.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.library.models.Author;
import practice.library.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AuthorService {

    private final AuthorRepository authorRepository;

//    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthorService(AuthorRepository authorRepository/*, PasswordEncoder passwordEncoder*/) {
        this.authorRepository = authorRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Author> index() {
//        PageRequest name = PageRequest.of(0, 15, Sort.by("name").descending());
        return authorRepository.findAll(Sort.by("name"));
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public List<Author> index(boolean sorted) {
////        if (sorted) {
////            return peopleRepository.findAll(Sort.by("dateOfBirth"));
////        }
////        else {
//            return authorRepository.findAll();
////        }
//    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public List<Author> index(Integer page, Integer peoplePerPage, boolean sorted) {
//        if(sorted) {
//
//            return authorRepository.findAll(PageRequest.of(page, peoplePerPage)).getContent();
////                    Sort.by("dateOfBirth"))).getContent();
//        } else return authorRepository.findAll(PageRequest.of(page, peoplePerPage)).getContent();
//    }

    //    @PreAuthorize("(principal.getPerson().getId == #id) or (hasRole('ROLE_ADMIN'))")
    public Author show(long id) {
        Optional<Author> foundPerson = authorRepository.findById(id);
        return foundPerson.orElse(null);
    }

//    public Optional<Author> loadUserByUsername(String username) {
//        return authorRepository.findPersonByName(username, Sort.by("name"));
//    }

    @Transactional
    public void save(Author person) {
        authorRepository.save(person);
    }

//    @Transactional
//    public void update(long id, Author updatedPerson) {
//        updatedPerson.setId(id);
////        updatedPerson.setRole("ROLE_USER");
////        updatedPerson.setPassword(passwordEncoder.encode(updatedPerson.getPassword()));
//        authorRepository.save(updatedPerson);
//    }
//
//    @Transactional
////    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public void delete(long id) {
//        authorRepository.deleteById(id);
//    }

    //    @PreAuthorize("principal.getPerson().getId == #id")
//    public List<Book> getBooks(long id) {
//        Optional<Author> personOptional = authorRepository.findById(id);
//
//        if (personOptional.isPresent()) {
//            Hibernate.initialize(personOptional.get().getBooks());
//            for (Book book : personOptional.get().getBooks()) {
//                final long convert = 24*60*60*1000;
////                if ((new Date().getTime() - book.getTakenAt().getTime())/convert >= 10) {
////                    book.setOverdue(true);
////                } else {
////                    book.setOverdue(false);
////                }
//            }
//            return personOptional.get().getBooks();
//        }
//        else
//            return Collections.emptyList();
//    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Author> search(String contain) {
        return authorRepository.findPersonByNameContainingIgnoreCase(contain, Sort.by("name"));
    }

//    public PersonDetails getPersonDetails() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        PersonDetails personDetails = (PersonDetails) auth.getPrincipal();
//        return personDetails;
//    }
}