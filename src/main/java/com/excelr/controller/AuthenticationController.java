package com.excelr.controller;


import com.excelr.dao.request.SignUpRequest;
import com.excelr.dao.request.SigninRequest;
import com.excelr.dao.response.JwtAuthenticationResponse;
import com.excelr.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request){

        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request){

        return  ResponseEntity.ok(authenticationService.signin(request));
    }
}
