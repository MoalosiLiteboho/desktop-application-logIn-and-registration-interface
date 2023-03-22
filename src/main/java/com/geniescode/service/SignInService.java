package com.geniescode.service;

import com.geniescode.dao.DAOImplementation;
import com.geniescode.dto.UserDTO;
import com.geniescode.dtoMapper.UserDTOMapper;
import com.geniescode.exception.NotFoundException;
import com.geniescode.model.Credentials;
import com.geniescode.model.User;

import javax.swing.JOptionPane;
import java.util.function.Consumer;

import static com.geniescode.validator.SignInValidator.isPasswordValid;
import static com.geniescode.validator.SignInValidator.isUsernameValid;

public class SignInService {
    private final Credentials credentials;
    private final DAOImplementation userDAO;
    public SignInService(Credentials credentials) {
        this.credentials = credentials;
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
        UserDTO userNamesAndId = getUserByUsernameAndId();
        displayDashboard(userNamesAndId);
    }

    private UserDTO getUserByUsernameAndId() {
        User userDetailsFromDatabase = userDAO.findUserByEmailAndPassword(credentials)
                .orElseThrow(() -> new NotFoundException("Email and Password you entered are INVALID! \n Please try again using CORRECT credentials!"));
        return new UserDTOMapper().apply(userDetailsFromDatabase);
    }

    private void displayDashboard(UserDTO user) {
        System.out.println(user);
    }

    private final Consumer<String> displayMessage = message -> JOptionPane.showMessageDialog(null, message);
}
