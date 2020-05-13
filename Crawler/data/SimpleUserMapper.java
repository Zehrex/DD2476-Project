2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/mapper/SimpleUserMapper.java
package com.gardle.service.mapper;

import com.gardle.domain.User;
import com.gardle.service.dto.SimpleUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleUserMapper {

    User toUser(SimpleUserDTO userDTO);

    SimpleUserDTO toDTO(User user);
}
