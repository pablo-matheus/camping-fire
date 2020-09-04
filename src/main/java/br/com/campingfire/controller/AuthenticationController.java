package br.com.campingfire.controller;

import br.com.campingfire.request.AuthenticationRequest;
import br.com.campingfire.response.AuthenticationResponse;
import br.com.campingfire.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(
            AuthenticationManager authenticationManager, 
            AuthenticationService authenticationService) 
    {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) {

        UsernamePasswordAuthenticationToken loginData = authenticationRequest.generateAuthenticationToken();

        try {

            Authentication authentication = authenticationManager.authenticate(loginData);
            String token = authenticationService.generateToken(authentication);
            return ResponseEntity.ok(new AuthenticationResponse(token, "Bearer"));

        } catch (AuthenticationException e) {

            return ResponseEntity.badRequest().build();

        }

    }

}
