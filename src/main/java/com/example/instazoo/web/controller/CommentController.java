package com.example.instazoo.web.controller;

import com.example.instazoo.dto.CommentDTO;
import com.example.instazoo.entity.Comment;
import com.example.instazoo.payload.response.MessageResponse;
import com.example.instazoo.services.CommentService;
import com.example.instazoo.validations.ResponseErrorValidation;
import com.example.instazoo.web.mappers.CommentMapper;
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
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final ResponseErrorValidation responseErrorValidation;

    @PostMapping("/{postId}/create")
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
    public ResponseEntity<List<CommentDTO>> getAllCommentsPost(@PathVariable("postId") String postId) {
        List<Comment> allCommentsForPost = commentService.getAllCommentsForPost(Long.parseLong(postId));
        List<CommentDTO> commentDTOList = commentMapper.toDto(allCommentsForPost);

        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable("commentId") String commentId) {
        commentService.deleteComment(Long.parseLong(commentId));

        return new ResponseEntity<>(new MessageResponse("Post was deleted"), HttpStatus.OK);
    }


}
