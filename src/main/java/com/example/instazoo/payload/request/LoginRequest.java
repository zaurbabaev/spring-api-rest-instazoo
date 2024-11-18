package com.example.instazoo.payload.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotEmpty(message = "Username cannot be empty")
    String username;
    @NotEmpty(message = "Password cannot be empty")
    String password;


}
