package com.jmv.studentManagement.filter;
import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jmv.studentManagement.service.impl.JwtServiceImpl;
import com.jmv.studentManagement.service.impl.UserDetailsServiceImpl;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	private final JwtServiceImpl jwtServiceImpl;
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	
	public JwtAuthFilter(JwtServiceImpl jwtServiceImpl, UserDetailsServiceImpl userDetailsServiceImpl) {
		super();
		this.jwtServiceImpl = jwtServiceImpl;
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    
                    String username = jwtServiceImpl.extractUsername(token);
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
                        if (jwtServiceImpl.isValid(token, userDetails)) {
                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            
                            authToken.setDetails(
                                    new WebAuthenticationDetailsSource().buildDetails(request));
                            
                            SecurityContextHolder.getContext().setAuthentication(authToken);
                        }
                    }
                    break; 
                }
            }
        }
        
        filterChain.doFilter(request, response);
    }
}

