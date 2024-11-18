package com.example.instazoo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "USER DTO")
public class CommentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Comment id", example = "1")
    Long id;

    @NotEmpty(message = "Message must not be null or empty")
    @Schema(description = "Comment message", example = "Hello how are you?")
    String message;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Username", example = "Cathrine18")
    String username;
}
