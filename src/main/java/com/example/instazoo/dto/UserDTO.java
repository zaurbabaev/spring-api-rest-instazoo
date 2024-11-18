package com.example.instazoo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "USER DTO")
public class UserDTO {

    @Schema(description = "User id", example = "1")
    Long id;

    @NotEmpty(message = "Firstname must not be null or empty")
    @Schema(description = "User firstname", example = "Jamie")
    String firstname;

    @NotEmpty(message = "Lastname must not be null or empty")
    @Schema(description = "User lastname", example = "Cruickshank")
    String lastname;

    @NotEmpty(message = "Username must not be null or empty")
    @Schema(description = "User email", example = "Elian_Kihn@hotmail.com")
    String username;

    @Schema(description = "User bio", example = "BIO")
    String bio;
}
