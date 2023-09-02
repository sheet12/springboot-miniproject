package com.miniproject.springboot.repository;

import com.miniproject.springboot.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    Set<Review> getReviewByBookId(Long bookId);
}
