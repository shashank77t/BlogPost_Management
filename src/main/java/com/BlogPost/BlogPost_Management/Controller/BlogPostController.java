package com.BlogPost.BlogPost_Management.Controller;


import com.BlogPost.BlogPost_Management.Dto.GeneralMsgDto;
import com.BlogPost.BlogPost_Management.Exception.BlogException;
import com.BlogPost.BlogPost_Management.Model.BlogPost;
import com.BlogPost.BlogPost_Management.Service.BlogPostService;
import com.BlogPost.BlogPost_Management.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {
    @Autowired
    BlogPostService blogPostService;
    @PostMapping("/create")
    public BlogPost createBlogPost(@RequestBody BlogPost blogPost){

        return blogPostService.createBlog(blogPost);
    }
    @GetMapping("getAllBlogs")
    public List<BlogPost> getAllBlogs(){
        return blogPostService.getAllBlogPosts();
    }
    @GetMapping("/getBlogPost")
    public ResponseEntity blogPostById(@RequestParam Integer id) {
        try{
            BlogPost b1=blogPostService.getBlogById(id);
            return new ResponseEntity<>(b1,HttpStatus.OK);
        }catch (BlogException b){
            return new ResponseEntity<>(new GeneralMsgDto(b.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateBlogPost")
    public ResponseEntity updateBlogPost(@RequestBody BlogPost blogPost){

        try{
            BlogPost b1=blogPostService.updateBlogPost(blogPost);
            return new ResponseEntity<>(b1,HttpStatus.OK);
        }catch (BlogException b){
            return new ResponseEntity<>(new GeneralMsgDto(b.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }





    }
     @DeleteMapping("/deleteBlogPost")
    public ResponseEntity deleteBlogPost(@RequestParam Integer id){
       try{
           blogPostService.deleteBlogPost(id);
           return new ResponseEntity<>(new GeneralMsgDto("Deleted successfully"),HttpStatus.OK);
       }catch(BlogException b){
           return new ResponseEntity<>(new GeneralMsgDto(b.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

}
