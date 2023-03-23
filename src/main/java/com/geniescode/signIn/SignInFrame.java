package com.geniescode.signIn;

import com.geniescode.share.components.buttons.Button;
import com.geniescode.share.components.passwordField.PasswordField;
import com.geniescode.share.components.textField.TextField;
import com.geniescode.share.components.tittleBar.ClosingBar;
import net.miginfocom.swing.MigLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.util.function.Consumer;

public class SignInFrame extends JFrame {
    private PasswordField password;
    private TextField email;
    private Button logIn;
    private Button registration;

    public SignInFrame() {
        initComponents();
    }

    private void initComponents() {
        Panel background = new Panel();
        JLabel tittle = new JLabel("SingIn");
        email = new TextField("Email", Color.green);
        password = new PasswordField(Color.green);
        logIn = new Button("LogIn");
        registration = new Button("Registration");

        tittle.setHorizontalAlignment(SwingConstants.CENTER);
        tittle.setFont(tittle.getFont().deriveFont(Font.PLAIN, 25));
        tittle.setForeground(Color.green);

        background.setLayout(new MigLayout("inset 0px, wrap"));
        background.add(new ClosingBar(), "width 100%");
        background.add(tittle, "width 100%, gap bottom 30px");
        background.add(email, "height 45px, width 60%, gap left 20%, gap bottom 5px");
        background.add(password, "height 45px, width 60%, gap left 20%, gap bottom 20px");
        background.add(logIn, "width 40%, height 35px, gap left 30%");
        background.add(registration, "width 40%, height 35px, gap left 30%, gap top 5px");
        background.setBackground(Color.white);

        setLayout(new BorderLayout());
        add(background);
        setSize(new Dimension(600, 350));
        setFont(new Font("sanserif", Font.PLAIN, 15));
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        addController.accept(this);
    }

    public void addButtonListener(SignInController controller) {
        logIn.addActionListener(controller);
        registration.addActionListener(controller);
    }

    private final Consumer<SignInFrame> addController = SignInController::new;

    public String getPassword() {
        return String.valueOf(password.getPassword());
    }

    public String getEmail() {
        return email.getText();
    }

    public Button getLogIn() {
        return logIn;
    }

    public Button getRegistration() {
        return registration;
    }
}
