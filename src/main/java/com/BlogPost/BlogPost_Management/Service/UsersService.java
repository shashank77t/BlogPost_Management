package com.BlogPost.BlogPost_Management.Service;

import com.BlogPost.BlogPost_Management.Model.Users;
import com.BlogPost.BlogPost_Management.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UsersRepository usersRepository;
    public Users register(Users users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return usersRepository.save(users);
    }
}
