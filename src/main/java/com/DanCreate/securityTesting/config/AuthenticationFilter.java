package com.DanCreate.securityTesting.config;

import com.DanCreate.securityTesting.reponsitory.TokenRepository;
import com.DanCreate.securityTesting.util.JwtServiceUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private  final JwtServiceUtil jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final  String authHeader = request.getHeader("Authorization");
        final  String jwt;
        final  String userEmail;
        if(authHeader == null || !authHeader.startsWith("bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.getUsernameFromToken(jwt);
        var isTokenValid = tokenRepository.findByToken(jwt)
                .map(t -> !t.isExpired() && !t.isRevoked())
                .orElse(false);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwt,userDetails)&& isTokenValid){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);

    }
}
