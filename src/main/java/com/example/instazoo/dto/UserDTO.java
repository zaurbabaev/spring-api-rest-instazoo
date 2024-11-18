package com.example.instazoo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    Long id;
    @NotEmpty(message = "Firstname must not be null or empty")
    String firstname;
    @NotEmpty(message = "Lastname must not be null or empty")
    String lastname;
    @NotEmpty(message = "Username must not be null or empty")
    String username;
    String bio;
}
