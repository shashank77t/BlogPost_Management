package com.BlogPost.BlogPost_Management.Controller;

import com.BlogPost.BlogPost_Management.Dto.JwtRequest;
import com.BlogPost.BlogPost_Management.Dto.JwtResponse;
import com.BlogPost.BlogPost_Management.Model.Users;
import com.BlogPost.BlogPost_Management.Security.UsersDetailsService;
import com.BlogPost.BlogPost_Management.Service.UsersService;
import com.BlogPost.BlogPost_Management.Utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
  private   UsersDetailsService usersDetailsService;
    @Autowired
    UsersService usersService;

    @PostMapping("/register")
    public Users register(@RequestBody Users users){
        return usersService.register(users);

    }
    @PostMapping("signin")
    public JwtResponse signin(@RequestBody JwtRequest jwtRequest) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("INVALID CREDENTIALS",e);
        }
        final UserDetails userDetails=usersDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token=jwtUtility.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
