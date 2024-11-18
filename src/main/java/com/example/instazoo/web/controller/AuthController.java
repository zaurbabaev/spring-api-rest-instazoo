package com.example.instazoo.web.controller;

import com.example.instazoo.payload.request.LoginRequest;
import com.example.instazoo.payload.request.SignUpRequest;
import com.example.instazoo.payload.response.JWTTokenSuccessResponse;
import com.example.instazoo.payload.response.MessageResponse;
import com.example.instazoo.security.JWTTokenProvider;
import com.example.instazoo.security.SecurityConstants;
import com.example.instazoo.services.UserService;
import com.example.instazoo.validations.ResponseErrorValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
@Tag(name = "Auth Controller", description = "Auth API")
public class AuthController {

    private final ResponseErrorValidation responseErrorValidation;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;


    @PostMapping("/signin")
    @Operation(summary = "With this method we AUTHENTICATION")
    public ResponseEntity<Object> authentication(@Valid @RequestBody LoginRequest loginRequest,
                                                 BindingResult bindingResult) {

        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }


    @PostMapping("/signup")
    @Operation(summary = "With this method we CREATE new User")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest,
                                               BindingResult bindingResult) {

        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        userService.createUser(signUpRequest);

        return new ResponseEntity<>(new MessageResponse("User registered successfully"), HttpStatus.CREATED);
    }


}
