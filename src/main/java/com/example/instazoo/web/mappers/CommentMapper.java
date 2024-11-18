package com.example.instazoo.web.mappers;

import com.example.instazoo.dto.CommentDTO;
import com.example.instazoo.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper extends Mappable<Comment, CommentDTO> {
}
