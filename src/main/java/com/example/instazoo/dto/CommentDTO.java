package com.example.instazoo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    @NotEmpty(message = "Message must not be null or empty")
    String message;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String username;
}
