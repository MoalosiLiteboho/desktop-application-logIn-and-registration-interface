package com.geniescode.signUp;

import com.geniescode.date.ExpiryDateGenerator;
import com.geniescode.share.id.UserIdGenerator;
import com.geniescode.share.model.User;
import com.geniescode.share.password.PasswordGenerator;

import java.util.function.Function;

public class SignUpDTOMapper implements Function<SignUp, User> {
    @Override
    public User apply(SignUp signUp) {
        return new User(
                new UserIdGenerator().get(),
                signUp.name(),
                signUp.surname(),
                signUp.gender(),
                signUp.dateOfBirth(),
                signUp.email(),
                4,
                new ExpiryDateGenerator().get(),
                true,
                new PasswordGenerator().apply(
                        signUp.name(),
                        signUp.surname())
        );
    }
}
