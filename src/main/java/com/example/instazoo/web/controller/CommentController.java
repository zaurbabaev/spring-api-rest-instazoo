package com.example.instazoo.web.controller;

import com.example.instazoo.dto.CommentDTO;
import com.example.instazoo.entity.Comment;
import com.example.instazoo.payload.response.MessageResponse;
import com.example.instazoo.services.CommentService;
import com.example.instazoo.validations.ResponseErrorValidation;
import com.example.instazoo.web.mappers.CommentMapper;
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
@RequestMapping("/api/comments")
@Tag(name = "Comment Controller", description = "Comment API")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final ResponseErrorValidation responseErrorValidation;

    @PostMapping("/{postId}/create")
    @Operation(summary = "With this method we CREATE COMMENT for the POST (use postId)")
    ResponseEntity<Object> createComment(@Valid @RequestBody CommentDTO commentDTO,
                                         @PathVariable("postId") String postId,
                                         BindingResult bindingResult,
                                         Principal principal) {

        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        commentDTO.setId(null);
        Comment comment = commentService.saveComment(Long.parseLong(postId), commentDTO, principal);
        CommentDTO createdComment = commentMapper.toDto(comment);

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/all")
    @Operation(summary = "With this method we GET ALL COMMENTS for the Post (use postId)")
    public ResponseEntity<List<CommentDTO>> getAllCommentsPost(@PathVariable("postId") String postId) {
        List<Comment> allCommentsForPost = commentService.getAllCommentsForPost(Long.parseLong(postId));
        List<CommentDTO> commentDTOList = commentMapper.toDto(allCommentsForPost);

        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "With this method we DELETE COMMENT by ID")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable("commentId") String commentId) {
        commentService.deleteComment(Long.parseLong(commentId));

        return new ResponseEntity<>(new MessageResponse("Comment was deleted"), HttpStatus.OK);
    }


}
