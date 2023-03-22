package com.geniescode.dtoMapper;

import com.geniescode.dto.UserDTO;
import com.geniescode.model.User;

import java.util.function.Function;

public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.Id(),
                user.name() + " " + user.surname()
        );
    }
}
