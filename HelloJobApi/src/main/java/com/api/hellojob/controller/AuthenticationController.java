package com.api.hellojob.controller;

import com.api.hellojob.entity.Company;
import com.api.hellojob.entity.JobSeeker;
import com.api.hellojob.jwt.JWTUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JWTUtils jwtUtils;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login-candidate")
    public ResponseEntity<String> authenticateJoSeeker(@RequestBody JobSeeker request){
        return getStringResponseEntity(request.getEmail(), request.getPassword(), request);
    }
    @PostMapping("/login-company")
    public ResponseEntity<String> authenticateCompany(@RequestBody Company request){
        return getStringResponseEntity(request.getEmail(), request.getPassword(), request);
    }

    private ResponseEntity<String> getStringResponseEntity(String email, String password, Object request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        final UserDetails user = userDetailsService.loadUserByUsername(email);
        if(user != null){
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }
}
