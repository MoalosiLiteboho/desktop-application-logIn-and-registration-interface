package com.geniescode.signIn;

import com.geniescode.email.EmailValidator;

import java.util.function.Function;

public interface SignInValidator extends Function<SignIn, String> {
    SignInValidator isUsernameValid = user -> {
        if (user.email().isEmpty())
            return  "Username is empty! \n Please enter email";
        if (!new EmailValidator().test(user.email()))
            return  "Email you entered is not in a CORRECT format \nPlease enter the email in a correct Format";
        return  "SUCCESS";
    };

    SignInValidator isPasswordValid = user -> !user.password().isEmpty() ?
            "SUCCESS" : "Password is empty! \n Please enter password";

    default SignInValidator and (SignInValidator others) {
        return user -> {
            String result = this.apply(user);
            return "SUCCESS".equals(result) ?
                    others.apply(user) : result;
        };
    }
}