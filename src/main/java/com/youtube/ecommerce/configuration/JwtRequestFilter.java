package com.youtube.ecommerce.configuration;

import com.youtube.ecommerce.service.JwtService;
import com.youtube.ecommerce.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    public static String USERNAME = "";
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Get the JWT token from the Authorization header of the incoming request
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        // If the Authorization header is not null and starts with "Bearer ", extract the token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);

            try {
                // Get the username from the token using a utility class
                username = jwtUtil.getUsernameFromToken(jwtToken);
                // Set the username as a static field of the class for future reference
                USERNAME = username;
            } catch (IllegalArgumentException e) {
                // If the token is invalid or cannot be parsed, log an error
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                // If the token is expired, log an error
                System.out.println("JWT Token has expired");
            }
        } else {
            // If the Authorization header is null or does not start with "Bearer ", log an error
            System.out.println("JWT token does not start with Bearer");
        }

        // If the username is not null and there is no existing authentication context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load the user details from the JWT using a service class
            UserDetails userDetails = jwtService.loadUserByUsername(username);

            // If the token is valid, create an authentication token and set it in the security context
            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // Call the next filter in the chain
        filterChain.doFilter(request, response);

    }

}
