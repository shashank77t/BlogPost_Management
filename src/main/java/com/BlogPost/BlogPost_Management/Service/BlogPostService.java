package com.BlogPost.BlogPost_Management.Service;

import com.BlogPost.BlogPost_Management.Exception.BlogException;
import com.BlogPost.BlogPost_Management.Model.BlogPost;
import com.BlogPost.BlogPost_Management.Model.Users;
import com.BlogPost.BlogPost_Management.Repository.BlogPostRepository;
import com.BlogPost.BlogPost_Management.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    BlogPostRepository blogPostRepository;
    public BlogPost createBlog(BlogPost blogPost){


        return blogPostRepository.save(blogPost);
    }
    public List<BlogPost> getAllBlogPosts(){
        return blogPostRepository.findAll();
    }
    public BlogPost getBlogById(Integer id) throws BlogException{
        return blogPostRepository.findById(id).orElseThrow(()->new BlogException("Blog not found with this id"));
    }
    public BlogPost updateBlogPost(BlogPost blogPost) throws BlogException{
        BlogPost blog=blogPostRepository.findById(blogPost.getId()).orElseThrow(()->new BlogException("There is no blogpost with the respective id"));
        blog.setContent(blogPost.getContent());

        blog.setTitle(blogPost.getTitle());
        return blog;
    }
    public BlogPost deleteBlogPost(Integer id) throws BlogException {
        BlogPost blog=blogPostRepository.findById(id).orElse(null);
        if(blog==null){
            throw new BlogException("blog not found with id");
        }
        blogPostRepository.deleteById(id);
        return blog;
    }
}
