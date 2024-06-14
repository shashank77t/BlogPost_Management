package com.BlogPost.BlogPost_Management.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {



        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
        @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
        @Column(name = "id")
        private Integer id;

        @Column(name = "username", nullable = false, unique = true)
        private String username;

        @Column(name = "password", nullable = false)
        private String password;




        // Getters and setters

}
