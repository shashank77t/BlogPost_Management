package com.BlogPost.BlogPost_Management.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor


public class BlogException extends Exception{
    private String msg;
    public BlogException(String msg){
        super(msg);
    }

}
