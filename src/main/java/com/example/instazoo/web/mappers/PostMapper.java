package com.example.instazoo.web.mappers;

import com.example.instazoo.dto.PostDTO;
import com.example.instazoo.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper extends Mappable<Post, PostDTO> {

}
