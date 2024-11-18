package com.example.instazoo.web.controller;

import com.example.instazoo.entity.ImageModel;
import com.example.instazoo.payload.response.MessageResponse;
import com.example.instazoo.services.ImageUpdateService;
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
public class ImageUploadController {

    private final ImageUpdateService imageUpdateService;

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadImageToUser(@RequestParam("file") MultipartFile file,
                                                             Principal principal) throws IOException {
        imageUpdateService.uploadImageToUser(file, principal);

        return ResponseEntity.ok(new MessageResponse("Image Uploaded Successfully"));
    }

    @PostMapping("/{postId}/upload")
    public ResponseEntity<MessageResponse> uploadImageToPost(@RequestParam("file") MultipartFile file,
                                                             @PathVariable("postId") String postId,
                                                             Principal principal) throws IOException {
        imageUpdateService.uploadImageToPost(file, principal, Long.parseLong(postId));

        return ResponseEntity.ok(new MessageResponse("Image Uploaded Successfully"));
    }

    @GetMapping("/profileImage")
    public ResponseEntity<ImageModel> getImageToUser(Principal principal) {
        ImageModel userImage = imageUpdateService.getImageToUser(principal);

        return new ResponseEntity<>(userImage, HttpStatus.OK);
    }

    @GetMapping("/{postId}/image")
    public ResponseEntity<ImageModel> getImageToPost(@PathVariable("postId") String postId) {
        ImageModel postImage = imageUpdateService.getImageToPost(Long.parseLong(postId));

        return new ResponseEntity<>(postImage, HttpStatus.OK);
    }


}
