package com.example.INIT_JAVA.controllers;


import com.example.INIT_JAVA.DTOs.request.JwtRequest;
import com.example.INIT_JAVA.DTOs.request.UserLoginRequestDto;
import com.example.INIT_JAVA.DTOs.response.JwtResponse;
import com.example.INIT_JAVA.DTOs.response.UserResponseDto;
import com.example.INIT_JAVA.security.JwtTokenUtil;
import com.example.INIT_JAVA.services.Implementation.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServiceImpl userDetailsService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserLoginRequestDto user) {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
