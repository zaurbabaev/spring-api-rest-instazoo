package com.example.instazoo.web.controller;

import com.example.instazoo.dto.PostDTO;
import com.example.instazoo.entity.Post;
import com.example.instazoo.entity.User;
import com.example.instazoo.payload.response.MessageResponse;
import com.example.instazoo.services.PostService;
import com.example.instazoo.services.UserService;
import com.example.instazoo.validations.ResponseErrorValidation;
import com.example.instazoo.web.mappers.PostMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/posts")
@Tag(name = "Post Controller", description = "Post API")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final ResponseErrorValidation responseErrorValidation;
    private final UserService userService;


    @PostMapping("/create")
    @Operation(summary = "With this method we CREATE new POST")
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDTO postDTO,
                                             BindingResult bindingResult,
                                             Principal principal) {

        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        User user = userService.getCurrentUser(principal);
        postDTO.setId(null);
        Post post = postService.createPost(postDTO, principal);

        PostDTO createdPost = postMapper.toDto(post);

        // postDto daxilində user yoxdur username var ona görədə mapper-dən sonra username ayrıca set edildi.
        createdPost.setUsername(user.getUsername());

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "With this method we GET ALL POSTS and ORDER it by CREATE DATE (DESC)")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<Post> postList = postService.getAllPosts();
        List<PostDTO> postDTOList = postMapper.toDto(postList);

        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @GetMapping("/user/posts")
    @Operation(summary = "With this method we GET ALL POSTS OF USERS and ORDER it by CREATE DATE (DESC)")
    public ResponseEntity<List<PostDTO>> getAllPostsForUser(Principal principal) {
        List<Post> allPostForUser = postService.getAllPostForUser(principal);
        List<PostDTO> postDTOList = postMapper.toDto(allPostForUser);

        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @PostMapping("{postId}/{username}/like")
    @Operation(summary = "With this method we like any POST use postId and username")
    public ResponseEntity<PostDTO> likePost(@PathVariable("postId") String postId,
                                            @PathVariable("username") String username) {
        Post post = postService.likePost(Long.parseLong(postId), username);
        PostDTO postDTO = postMapper.toDto(post);

        // postDTO daxilində user yoxdur username var ona görə postdan useri onuda daxilində usernameni götürük
        postDTO.setUsername(post.getUser().getUsername());

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "With this method we DELETE POST by ID")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable("postId") String postId,
                                                      Principal principal) {
        postService.deletePost(Long.parseLong(postId), principal);
        return new ResponseEntity<>(new MessageResponse("Post was deleted"), HttpStatus.OK);
    }


}
