package com.example.instazoo.web.controller;

import com.example.instazoo.entity.ImageModel;
import com.example.instazoo.payload.response.MessageResponse;
import com.example.instazoo.services.ImageUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/images")
@Tag(name = "Image Upload Controller", description = "Image Upload API")
public class ImageUploadController {

    private final ImageUpdateService imageUpdateService;

    @PostMapping("/upload")
    @Operation(summary = "With this method we UPLOAD USERS image")
    public ResponseEntity<MessageResponse> uploadImageToUser(@RequestParam("file") MultipartFile file,
                                                             Principal principal) throws IOException {
        imageUpdateService.uploadImageToUser(file, principal);

        return ResponseEntity.ok(new MessageResponse("Image Uploaded Successfully"));
    }

    @PostMapping("/{postId}/upload")
    @Operation(summary = "With this method we UPLOAD image for the POST (use postId)")
    public ResponseEntity<MessageResponse> uploadImageToPost(@RequestParam("file") MultipartFile file,
                                                             @PathVariable("postId") String postId,
                                                             Principal principal) throws IOException {
        imageUpdateService.uploadImageToPost(file, principal, Long.parseLong(postId));

        return ResponseEntity.ok(new MessageResponse("Image Uploaded Successfully"));
    }

    @GetMapping("/profileImage")
    @Operation(summary = "With this method we get PROFILE IMAGE for the USER")
    public ResponseEntity<ImageModel> getImageToUser(Principal principal) {
        ImageModel userImage = imageUpdateService.getImageToUser(principal);

        return new ResponseEntity<>(userImage, HttpStatus.OK);
    }

    @GetMapping("/{postId}/image")
    @Operation(summary = "With this method we get IMAGE for POST (use postId)")
    public ResponseEntity<ImageModel> getImageToPost(@PathVariable("postId") String postId) {
        ImageModel postImage = imageUpdateService.getImageToPost(Long.parseLong(postId));

        return new ResponseEntity<>(postImage, HttpStatus.OK);
    }


}
