package com.geniescode.controller;

import com.geniescode.model.Credentials;
import com.geniescode.service.SignInService;
import com.geniescode.frame.SignIn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInController  implements ActionListener {
    private final SignIn signIn;

    public SignInController(SignIn signIn) {
        this.signIn = signIn;
        this.signIn.addButtonListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == signIn.getLogIn())
            signInAction();
        if (event.getSource() == signIn.getRegistration())
            registrationAction();
    }

    private void signInAction() {
        new SignInService(
                new Credentials(
                        signIn.getEmail(),
                        signIn.getPassword())
        );
    }

    private void registrationAction() {
        // add registration frame
        signIn.dispose();
    }
}
