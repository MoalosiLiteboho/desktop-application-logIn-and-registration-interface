package com.geniescode.signUp;

import com.geniescode.dao.DAOImplementation;
import com.geniescode.share.model.User;

import javax.swing.JOptionPane;
import java.util.function.Consumer;

import static com.geniescode.signUp.SignUpValidator.isDateOfBirthValid;
import static com.geniescode.signUp.SignUpValidator.isEmailValid;
import static com.geniescode.signUp.SignUpValidator.isGenderValid;
import static com.geniescode.signUp.SignUpValidator.isNameValid;
import static com.geniescode.signUp.SignUpValidator.isSurnameValid;

public class SignUpService {
    private final SignUpFrame registration;
    private final SignUp signUp;

    public SignUpService(SignUpFrame registration, SignUp signUp) {
        this.registration = registration;
        this.signUp = signUp;
        registrationProcess();
    }


    private void registrationProcess() {
        String result = isNameValid
                .and(isSurnameValid)
                .and(isDateOfBirthValid)
                .and(isGenderValid)
                .and(isEmailValid)
                .apply(signUp);

        registerUserIfAllFieldAreFilled(result, displayMessage);
    }

    private void registerUserIfAllFieldAreFilled(String result, Consumer<String> showUserError) {
        if(!"SUCCESS".equals(result)) {
            showUserError.accept(result);
            return;
        }
        registration();
    }

    private void registration() {
        User user = new SignUpDTOMapper().apply(signUp);
        new DAOImplementation().saveUser(user);
        displayMessage.accept(signUp.name() + " " + signUp.surname() + " has registered successfully \nPassword is: " + signUp.name() + "@" + signUp.surname() + "12345");
        clearFields.accept(registration);
    }

    private final Consumer<SignUpFrame> clearFields = registration -> {
        registration.clearName();
        registration.clearSurname();
        registration.clearDateOfBirth();
        registration.clearGender();
        registration.clearEmail();
    };
    private final Consumer<String> displayMessage = message -> JOptionPane.showMessageDialog(null, message);
}