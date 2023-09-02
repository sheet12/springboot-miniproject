package com.miniproject.springboot.service;

import com.miniproject.springboot.entity.Review;
import com.miniproject.springboot.util.ReviewResponse;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    Review createReview(Review newReview);
    Review findReviewById(Long reviewId);
    ReviewResponse findAllReview(int pageNo, int pageSize, String sortBy, String sortDir);
    Review updateReview(Long reviewId, Review newReview);
    void deleteReview(Long reviewId);

}
