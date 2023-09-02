package com.miniproject.springboot.service.impl;

import com.miniproject.springboot.entity.Book;
import com.miniproject.springboot.entity.Review;
import com.miniproject.springboot.exception.ResourceNotFoundException;
import com.miniproject.springboot.repository.BookRepository;
import com.miniproject.springboot.repository.ReviewRepository;
import com.miniproject.springboot.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_ID= "BookId";
    private static final String BOOK= "Book";

    private final BookRepository bookRepository;

    private final ReviewRepository reviewRepository;


    @Override
    public List<Book> findAllBook(String bookCategory) {
        List<Book> bookList;
        if (bookCategory != null && !"null".equals(bookCategory)) {
            bookList = bookRepository.findByBookCategory(bookCategory);
        }else {
            bookList = bookRepository.findAll();
        }
        if(!bookList.isEmpty()) {
            for (Book bookDetail : bookList) {
                Set<Review> reviewSet = reviewRepository.getReviewByBookId(bookDetail.getId());
                bookDetail.setReviewSet(reviewSet);
            }
        }
        return bookList;
    }

    @Override
    public Book findBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException(BOOK,BOOK_ID,bookId));
        Set<Review> reviewSet = reviewRepository.getReviewByBookId(bookId);
        book.setReviewSet(reviewSet);
        return book;
    }

    @Override
    public Book addNewBookDetails(Book newBookDetails) {
        Set<Review> reviewSet = new HashSet<>();
        for(Review review: newBookDetails.getReviewSet()){
            Review reviews = reviewRepository.findById(review.getId()).orElseThrow(()-> new ResourceNotFoundException("Review","ReviewId",review.getId()));
            reviewSet.add(reviews);
        }
        newBookDetails.setReviewSet(reviewSet);
        return bookRepository.save(newBookDetails);
    }

    @Override
    public Book updateBookDetails(Long bookId, Book newBookDetails) {
        Set<Review> reviewSet = new HashSet<>();
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException(BOOK,BOOK_ID,bookId));
        for(Review review: newBookDetails.getReviewSet()) {
            Review reviews = reviewRepository.findById(review.getId()).orElseThrow(() -> new ResourceNotFoundException("Review", "ReviewId", review.getId()));
            reviews.setBookId(book.getId());
            reviewSet.add(reviews);
            reviewRepository.save(reviews);
        }
        book.setTitle(newBookDetails.getTitle());
        book.setAuthor(newBookDetails.getAuthor());
        book.setDescription(newBookDetails.getDescription());
        book.setBookCategory(newBookDetails.getBookCategory());
        book.setImage(newBookDetails.getImage());
        bookRepository.save(book);
        book.setReviewSet(reviewSet);
        return book;
    }

    @Override
    public void deleteBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException(BOOK,BOOK_ID,bookId));
        bookRepository.deleteById(book.getId());
    }
}
