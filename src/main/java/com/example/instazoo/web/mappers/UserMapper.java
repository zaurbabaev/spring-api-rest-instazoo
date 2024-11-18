package com.example.instazoo.web.mappers;

import com.example.instazoo.dto.UserDTO;
import com.example.instazoo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDTO> {

}
