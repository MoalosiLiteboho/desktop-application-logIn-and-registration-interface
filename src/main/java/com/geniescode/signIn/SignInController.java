package com.geniescode.signIn;

import com.geniescode.signUp.SignUpFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInController  implements ActionListener {
    private final SignInFrame signInFrame;

    public SignInController(SignInFrame signInFrame) {
        this.signInFrame = signInFrame;
        this.signInFrame.addButtonListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == signInFrame.getLogIn())
            signInAction();
        if (event.getSource() == signInFrame.getRegistration())
            registrationAction();
    }

    private void signInAction() {
        new SignInService(
                new SignIn(
                        signInFrame.getEmail(),
                        signInFrame.getPassword())
        );
    }

    private void registrationAction() {
        new SignUpFrame().setVisible(true);
        signInFrame.dispose();
    }
}
