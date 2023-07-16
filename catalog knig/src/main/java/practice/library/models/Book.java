package practice.library.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotEmpty
    @Size(max = 50, message = "Название книги не может содержать более 50 символов")
    private String title;

    @Column(name = "date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateAdded;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "author_book",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")})
    private List<Author> author;

    public Book() {
    }

    public Book(String title, List<Author> author, Timestamp dateAdded) {
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

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public void addAuthor(Author author) {
        if (Objects.isNull(this.author))
            this.author = new ArrayList<>();
        this.author.add(author);
    }


    @Override
    public String toString() {
        return "Book{" +

                "id=" + id +
                ", title='" + title + '\'' +
//                ", yearOfProd=" + yearOfProd +
                ", author=" + (author != null ? author.toArray() : "null") +
                '}';
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Book book = (Book) obj;
//        if (Objects.equals(this.id, book.getId()))
//            return true;
//        else return false;
//    }
}
