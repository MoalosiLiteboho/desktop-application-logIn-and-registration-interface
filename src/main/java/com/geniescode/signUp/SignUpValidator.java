package com.geniescode.signUp;

import com.geniescode.date.DateValidator;
import com.geniescode.email.EmailTaken;
import com.geniescode.email.EmailValidator;

import java.util.function.Function;

public interface SignUpValidator extends Function<SignUp, String> {
    SignUpValidator isNameValid = user -> !user.name().isEmpty() ?
            "SUCCESS" : "Name is Empty \n Please enter name";

    SignUpValidator isSurnameValid = user -> !user.surname().isEmpty() ?
            "SUCCESS" : "Surname is Empty \nPlease enter surname";

    SignUpValidator isDateOfBirthValid = user -> new DateValidator().test(user.dateOfBirth()) ?
            "SUCCESS" : "You entered invalid birth date \nPlease enter the correct birthdate ";

    SignUpValidator isGenderValid = user -> !user.gender().isEmpty() ?
            "SUCCESS" : "gender not selected \nPlease selected gender";

    SignUpValidator isEmailValid = user -> {
        if(user.email().isEmpty())
            return  "Email field is empty! \n Please enter email";
        else if(!new EmailValidator().test(user.email()))
            return  "Email you entered is not in a CORRECT format \nPlease enter the email in a correct Format";
        else if(new EmailTaken().test(user.email()))
            return "the email is taken please try to enter the other email or create new one ";
        else
            return  "SUCCESS";
    };

    default SignUpValidator and (SignUpValidator others) {
        return user -> {
            String result = this.apply(user);
            return "SUCCESS".equals(result) ?
                    others.apply(user) : result;
        };
    }
}
