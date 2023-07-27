package com.codewithdurgesh.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.payloads.JwtAuthRequest;
import com.codewithdurgesh.blog.payloads.JwtAuthResponse;
import com.codewithdurgesh.blog.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	
	@Autowired
	private JwtTokenHelper jwtTokenhelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request){
		
		this.authenticate(request.getEmail(),request.getPassword());
		
		UserDetails userDetail = this.userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.jwtTokenhelper.generateToken(userDetail);
		
		JwtAuthResponse authResponse = new JwtAuthResponse();
		authResponse.setToken(token);
		
		return new ResponseEntity<JwtAuthResponse>(authResponse,HttpStatus.OK);
	}


	private void authenticate(String email, String password) {
		// TODO Auto-generated method stub
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
		this.authenticationManager.authenticate(authenticationToken);
		
	}
}
