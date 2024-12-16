package com.sdp.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sdp.Enum.EmployeeOrAdmin;
import com.sdp.model.Employee;
import com.sdp.repository.EmployeeRepository;
import com.sdp.service.JobCandidateService;

@Configuration
public class MySecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Autowired
	private AuthenticationFilter filter;
	

	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		.csrf()
        .disable().exceptionHandling()
        .authenticationEntryPoint(entryPoint)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**") // Allow all OPTIONS requests
        .permitAll()
        .antMatchers("/admin/login","/employee/login","/admin/register")
        .permitAll()
        .antMatchers("/admin/**")
        .hasAuthority("ADMIN")
        .and()
        .httpBasic();
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		
		return configuration.getAuthenticationManager();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		
		
		return new BCryptPasswordEncoder();
	}
	
}
