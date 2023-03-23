package com.geniescode.signIn;

import com.geniescode.share.model.User;

import java.util.function.Function;

public class SignInDTOMapper implements Function<User, SignInDTO> {
    @Override
    public SignInDTO apply(User user) {
        return new SignInDTO(
                user.Id(),
                user.name() + " " + user.surname()
        );
    }
}
