package com.example.instazoo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDTO {

    Long id;
    @NotEmpty(message = "Message must not be null or empty")
    String message;
    String username;
}
