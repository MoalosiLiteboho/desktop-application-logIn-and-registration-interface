package com.geniescode.signIn;

import com.geniescode.dao.DAOImplementation;
import com.geniescode.share.exception.DisabledException;
import com.geniescode.share.exception.NotFoundException;
import com.geniescode.share.model.User;

import javax.swing.JOptionPane;
import java.util.function.Consumer;

import static com.geniescode.signIn.SignInValidator.isPasswordValid;
import static com.geniescode.signIn.SignInValidator.isUsernameValid;

public class SignInService {
    private final SignIn credentials;
    private final DAOImplementation userDAO;
    public SignInService(SignIn signIn) {
        this.credentials = signIn;
        userDAO = new DAOImplementation();
        logInProcess();
    }

    public void logInProcess() {
        String result = isUsernameValid
                .and(isPasswordValid)
                .apply(credentials);

        tryToLogInIfAllFieldAreFilled(result, displayMessage);
    }

    private void tryToLogInIfAllFieldAreFilled(String result, Consumer<String> showUserError) {
        if(!"SUCCESS".equals(result)) showUserError.accept(result);
        else logIn();
    }

    private void logIn() {
        SignInDTO userNamesAndId = getUserByUsernameAndId();
        displayDashboard(userNamesAndId);
    }

    private SignInDTO getUserByUsernameAndId() {
        return new SignInDTOMapper().apply(
                userDAO.findUserByEmailAndPassword(credentials)
                        .filter(User::isEnabled)
                        .orElseThrow(() -> {
                            if (userDAO.findUserByEmailAndPassword(credentials).isPresent())
                                return new DisabledException("Your account is disabled. Please contact support for assistance.", true);
                            else
                                return new NotFoundException("Email and Password you entered are INVALID! \n Please try again using CORRECT credentials!", true);
                        }));
    }

    private void displayDashboard(SignInDTO user) {
        System.out.println(user);
    }

    private final Consumer<String> displayMessage = message -> JOptionPane.showMessageDialog(null, message);
}
