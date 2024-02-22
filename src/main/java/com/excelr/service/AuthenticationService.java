package com.excelr.service;

import com.excelr.dao.request.SignUpRequest;
import com.excelr.dao.request.SigninRequest;
import com.excelr.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {


    JwtAuthenticationResponse signup(SignUpRequest request);
    JwtAuthenticationResponse signin(SigninRequest request);




}
