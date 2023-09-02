package com.miniproject.springboot.service.impl;

import com.miniproject.springboot.entity.Book;
import com.miniproject.springboot.entity.Review;
import com.miniproject.springboot.exception.ResourceNotFoundException;
import com.miniproject.springboot.repository.BookRepository;
import com.miniproject.springboot.repository.ReviewRepository;
import com.miniproject.springboot.service.ReviewService;
import com.miniproject.springboot.util.ReviewResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private static final String REVIEW_ID= "ReviewId";
    private static final String REVIEW= "Review";

    private final ReviewRepository reviewRepository;

    private final BookRepository bookRepository;

    @Override
    public Review createReview(Review newReview) {
        Book book = bookRepository.findById(newReview.getBookId()).orElseThrow(()-> new ResourceNotFoundException("Book","BookId", newReview.getBookId()));
        return reviewRepository.save(newReview);
    }

    @Override
    public Review findReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(()-> new ResourceNotFoundException(REVIEW,REVIEW_ID, reviewId));
    }

    @Override
    public ReviewResponse findAllReview(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Review> reviews = reviewRepository.findAll(pageable);
        List<Review> reviewList = reviews.getContent();
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setContent(reviewList);
        reviewResponse.setPageNo(pageNo);
        reviewResponse.setPageSize(pageSize);
        reviewResponse.setLast(reviews.isLast());
        reviewResponse.setTotalElement(reviews.getTotalElements());
        reviewResponse.setTotalPages(reviews.getTotalPages());
        return reviewResponse;
    }

    @Override
    public Review updateReview(Long reviewId, Review newReview) {
         bookRepository.findById(newReview.getBookId()).orElseThrow(()-> new ResourceNotFoundException("Book","BookId", newReview.getBookId()));
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new ResourceNotFoundException(REVIEW,REVIEW_ID, reviewId));

        review.setComment(newReview.getComment());
        review.setBookId(newReview.getBookId());
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new ResourceNotFoundException(REVIEW,REVIEW_ID, reviewId));
        reviewRepository.deleteById(review.getId());
    }
}
