package com.example.instazoo.payload.request;

import com.example.instazoo.annotations.PasswordMatches;
import com.example.instazoo.annotations.ValidEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@PasswordMatches
public class SignUpRequest {

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    String email;

    @NotEmpty(message = "Please enter your name")
    String firstname;

    @NotEmpty(message = "Please enter your lastname")
    String lastname;

    @NotEmpty(message = "Please enter your username")
    String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    String password;

    String confirmPassword;

}
