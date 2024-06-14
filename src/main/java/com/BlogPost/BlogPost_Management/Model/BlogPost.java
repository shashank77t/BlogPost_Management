package com.BlogPost.BlogPost_Management.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "blog_posts")
public class BlogPost {


        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_seq")
        @SequenceGenerator(name = "blog_seq", sequenceName = "blog_sequence", allocationSize = 1)
        private Integer id;

        @Column(nullable = false)
        private String title;

        @Column(nullable = false, columnDefinition = "TEXT")
        private String content;


        // Getters and setters

}
