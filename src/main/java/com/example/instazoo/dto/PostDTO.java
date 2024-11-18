package com.example.instazoo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "POST DTO")
public class PostDTO {

    @Schema(description = "Post id", example = "1")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    @Schema(description = "Post title", example = "My favorite pet")
    String title;

    @Schema(description = "Post caption", example = "I love pets")
    String caption;

    @Schema(description = "Post location", example = "California")
    String location;

    @Schema(description = "User username", example = "Cathrine18")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String username;

    @Schema(description = "User post likes", example = "12")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Integer likes;

    @Schema(description = "Who liked my post", example = "Set of users")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Set<String> usersLiked;


}
