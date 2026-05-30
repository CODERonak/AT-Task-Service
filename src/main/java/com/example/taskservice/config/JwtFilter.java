package com.example.taskservice.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.taskservice.client.AuthServiceClient;

import java.io.IOException;

@Component
@RequiredArgsConstructor
// OncePerRequestFilter = runs exactly once per HTTP request
public class JwtFilter extends OncePerRequestFilter {

    private final AuthServiceClient authServiceClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // Read the "Authorization" header: "Bearer eyJhbGciOi..."
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // No token provided — reject with 401 Unauthorized
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token");
            return;
        }

        // Strip "Bearer " prefix to get the raw token
        String token = authHeader.substring(7);

        // Ask Auth Service if this token is valid
        if (!authServiceClient.validateToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        // Token is valid — store username in request attribute for controllers
        String email = authServiceClient.getUsernameFromToken(token);
        request.setAttribute("email", email);

        // Pass request along to the next filter/controller
        filterChain.doFilter(request, response);
    }
}