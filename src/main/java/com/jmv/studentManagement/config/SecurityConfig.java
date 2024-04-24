package com.jmv.studentManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jmv.studentManagement.filter.JwtAuthFilter;
import com.jmv.studentManagement.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final JwtAuthFilter jwtAuthFilter;
	
	public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, JwtAuthFilter jwtAuthFilter) {
		super();
		this.userDetailsServiceImpl = userDetailsServiceImpl;
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						req->req.requestMatchers("/api/login/**","/api/register/**","/api/signin/**","/api/signup/**")
						.permitAll()
						.requestMatchers("/api/users/**").hasAuthority("SUPERUSER")
						.requestMatchers("/api/students/**").hasAnyAuthority("SUPERUSER","ADMIN")
						.requestMatchers("/api/student/{id}/get/**","/api/student/{id}/edit/**","/api/student/{userId}/add/**","/api/student/{userId}/search/**","/api/token/**","/api/user/**","/api/{userId}/students/**").hasAnyAuthority("SUPERUSER","ADMIN","USER")
						.anyRequest()
						.authenticated())
				.userDetailsService(userDetailsServiceImpl)
				.sessionManagement(session->session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	  
}

