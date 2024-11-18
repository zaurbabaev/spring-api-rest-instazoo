package com.example.instazoo.payload.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Request for Login")
public class LoginRequest {

    @NotEmpty(message = "Username cannot be empty")
    @Schema(description = "email", example = "Elian_Kihn@hotmail.com")
    String username;

    @NotEmpty(message = "Password cannot be empty")
    @Schema(description = "password", example = "qwerty123")
    String password;


}
