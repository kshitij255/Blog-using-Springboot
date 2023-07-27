package com.codewithdurgesh.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.codewithdurgesh.blog.security.CustomUserDetailService;
import com.codewithdurgesh.blog.security.JwtAuthenticationEntryPoint;
import com.codewithdurgesh.blog.security.JwtAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  
		  http.
		  csrf().disable()
		  .exceptionHandling().authenticationEntryPoint(this.authenticationEntryPoint)
		  .and()
		  .sessionManagement()
		  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		  .and()
		  .authorizeRequests()
		  .an
		  
		  http.addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		  
		  return http.build();
	  }
	
	  @Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(this.customUserDetailService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }
	
	  @Bean
	    PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	  
	  
	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
		  
	  }
	  
}
