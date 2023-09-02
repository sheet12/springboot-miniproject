package com.miniproject.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private Long bookId;
    @Lob
    @Column(name = "imageData",columnDefinition = "LONGBLOB")
    private byte[] image;
}
