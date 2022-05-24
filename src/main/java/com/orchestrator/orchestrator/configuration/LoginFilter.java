package com.orchestrator.orchestrator.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchestrator.orchestrator.model.dto.user.request.UserAuthenticateRequestDto;
import com.orchestrator.orchestrator.model.dto.user.response.UserAuthenticateResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    public LoginFilter(String loginUrl, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(loginUrl), authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        InputStream body = request.getInputStream();
        UserAuthenticateRequestDto user = new ObjectMapper().readValue(body, UserAuthenticateRequestDto.class);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUniqueIdentifier(), user.getPassword());
        return getAuthenticationManager().authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        User user = (User) auth.getPrincipal();
        String accessToken = JwtUtil.createToken(user);
        UserAuthenticateResponseDto userResponse = new UserAuthenticateResponseDto();
        userResponse.setIsAuthenticated(true);
        userResponse.setJwt(accessToken);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), userResponse);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        UserAuthenticateResponseDto userResponse = new UserAuthenticateResponseDto();
        userResponse.setIsAuthenticated(false);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        new ObjectMapper().writeValue(response.getOutputStream(), userResponse);
    }
}
