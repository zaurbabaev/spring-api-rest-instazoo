package com.example.instazoo.payload.request;

import com.example.instazoo.annotations.PasswordMatches;
import com.example.instazoo.annotations.ValidEmail;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "email", example = "Elian_Kihn@hotmail.com")
    String email;

    @NotEmpty(message = "Please enter your name")
    @Schema(description = "Firstname", example = "Jamie")
    String firstname;

    @NotEmpty(message = "Please enter your lastname")
    @Schema(description = "Lastname", example = "Cruickshank")
    String lastname;

    @NotEmpty(message = "Please enter your username")
    @Schema(description = "Username", example = "Cathrine18")
    String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    @Schema(description = "Password", example = "qwerty123")
    String password;

    @Schema(description = "Confirm password", example = "qwerty123")
    String confirmPassword;

}
