package com.orchestrator.orchestrator.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JwtFilter extends OncePerRequestFilter {
    private final List<String> excludedUrls;

    public JwtFilter(String... excludedUrls) {
        super();
        this.excludedUrls = new ArrayList<>(Arrays.asList(excludedUrls));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (excludedUrls.contains(request.getServletPath())) {
            filterChain.doFilter(request,response);
        } else {
            Authentication authentication = JwtUtil.getAuthenticationFromRequest();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request,response);
        }
    }
}
