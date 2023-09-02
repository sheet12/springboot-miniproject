package com.miniproject.springboot.util;


import com.miniproject.springboot.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private List<Review> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private long totalElement;
    private boolean last;
}
