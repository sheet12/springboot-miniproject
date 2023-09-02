package com.miniproject.springboot.controller;

import com.miniproject.springboot.designpattern.ResponseEntityBuilder;
import com.miniproject.springboot.entity.Book;
import com.miniproject.springboot.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/book")
@AllArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> findAllBook(@RequestParam(value = "bookCategory",required = false) String category){
        List<Book> bookResponse = bookService.findAllBook(category);
        return ResponseEntityBuilder.build(bookResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book newBook)  {
        Book book = bookService.addNewBookDetails(newBook);
        return ResponseEntityBuilder.build(book, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id,@Valid @RequestBody Book newBook){
        Book book = bookService.updateBookDetails(id,newBook);
        return ResponseEntityBuilder.build(book, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return "Book deleted successfully";
    }

}
