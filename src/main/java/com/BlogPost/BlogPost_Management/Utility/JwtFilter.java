package com.BlogPost.BlogPost_Management.Utility;


import com.BlogPost.BlogPost_Management.Security.UsersDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter{
//    @Autowired
//    private com.cabManagement.cabManagementSystem.Utility.JwtUtility jwtUtility;
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private UsersDetailsService usersDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization=request.getHeader("Authorization");
        String token=null;
        String userName=null;
        if(authorization!=null&&authorization.startsWith("Bearer ")){
            token=authorization.substring(7);
            userName=jwtUtility.getUsernameFromToken(token);

        }
        if(userName!=null&&SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails= usersDetailsService.loadUserByUsername(userName);
            if(jwtUtility.validateToken(token,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }

}
