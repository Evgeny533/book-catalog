package practice.library.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.library.dto.AuthorDTO;
import practice.library.dto.BookDTO;
import practice.library.dto.ResponseDto;
import practice.library.models.Author;
import practice.library.services.AuthorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController()
@RequestMapping("/api/v1/author")
public class AuthorController {


    private final AuthorService authorService;


    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @JsonView(BookDTO.NotAllInfo.class)
    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseDto> getAll(@RequestParam(value = "title", required = false) String pageNumber) {
        List<Author> authors = authorService.index();
        ResponseDto responseDto = new ResponseDto();
        if (Objects.nonNull(authors) && !authors.isEmpty()) {
            List<AuthorDTO> result = new ArrayList<>();
            for (Author author : authors) {
                result.add(AuthorDTO.fromAuthor(author));
            }
            responseDto.setResponse(1, "SUCCESS", "Авторы найдены", result);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            responseDto.setResponse(-1, "FAIL", "Авторы не найдены", null);
            return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
        }
    }

    @JsonView(BookDTO.AllInfo.class)
    @GetMapping(value = "/getAuthorById")
    public ResponseEntity<ResponseDto> getAuthorById(@RequestParam(value = "id", required = true) Long authorId) {


        Author authors = authorService.show(authorId);
        ResponseDto responseDto = new ResponseDto();
        if (Objects.nonNull(authors)) {
            AuthorDTO authorDTO = AuthorDTO.fromAuthor(authors);

            responseDto.setResponse(1, "SUCCESS", "Автор найден", authorDTO);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            responseDto.setResponse(-1, "FAIL", "Автор не найден", null);
            return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
        }
    }

    @JsonView(BookDTO.AllInfo.class)
    @PostMapping(value = "/addAuthor")
    public ResponseEntity<ResponseDto> addAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = AuthorDTO.fromAuthorDTO(authorDTO);
        authorService.save(author);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse(1, "SUCCESS", "Авторы найдены", author.getId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @JsonView(BookDTO.NotAllInfo.class)
    @GetMapping(value = "/getAuthorByName")
    public ResponseEntity<ResponseDto> getAuthorByName(@RequestParam(value = "name", required = true) String name) {

        List<Author> authors = authorService.search(name);
        ResponseDto responseDto = new ResponseDto();
        if (Objects.nonNull(authors) && !authors.isEmpty()) {
            List<AuthorDTO> result = new ArrayList<>();
            for (Author author : authors) {
                AuthorDTO authorDTO = AuthorDTO.fromAuthor(author);
                result.add(authorDTO);
            }
            responseDto.setResponse(1, "SUCCESS", "Авторы найдены", result);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            responseDto.setResponse(-1, "FAIL", "Автор не найден", null);
            return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
        }
    }

}