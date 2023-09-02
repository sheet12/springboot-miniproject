package com.miniproject.springboot.controller;

import com.miniproject.springboot.designpattern.ResponseEntityBuilder;
import com.miniproject.springboot.entity.Review;
import com.miniproject.springboot.service.ReviewService;
import com.miniproject.springboot.util.AppConstant;
import com.miniproject.springboot.util.ReviewResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/review/")
@RestController
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Review> createReview(@Valid @RequestBody Review newReview) {
        Review review= reviewService.createReview(newReview);
        return ResponseEntityBuilder.build(review, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Review> findReviewById(@PathVariable("id") Long reviewId) {
        Review review= reviewService.findReviewById(reviewId);
        return ResponseEntityBuilder.build(review, HttpStatus.OK);
    }

    @GetMapping
    public ReviewResponse findAllReview(@RequestParam(value = "pageNo",defaultValue = AppConstant.PAGE_NO) int pageNo,
                                                        @RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE) int pageSize,
                                                        @RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY) String sortBy,
                                                        @RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR) String sortDir){
        return reviewService.findAllReview(pageNo,pageSize,sortBy,sortDir);
    }

    @PutMapping("{id}")
    public ResponseEntity<Review> updateReview(@PathVariable("id") Long reviewId,@Valid @RequestBody Review newReview) {
        Review review = reviewService.updateReview(reviewId,newReview);
        return ResponseEntityBuilder.build(review,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public String deleteReview(@PathVariable("id")Long reviewId) {
        reviewService.deleteReview(reviewId);
        return "Review deleted successfully";
    }

}
