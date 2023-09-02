package com.miniproject.springboot.service;

import com.miniproject.springboot.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    Book findBookById(Long bookId);
    List<Book> findAllBook(String bookCategory);
    Book addNewBookDetails(Book book);
    Book updateBookDetails(Long bookId,Book book);
    void deleteBookById(Long bookId);
}
