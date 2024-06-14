package com.BlogPost.BlogPost_Management.Repository;

import com.BlogPost.BlogPost_Management.Model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost,Integer> {
}
