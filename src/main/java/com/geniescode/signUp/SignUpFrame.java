package com.geniescode.signUp;

import com.geniescode.share.components.buttons.Button;
import com.geniescode.share.components.comboBox.ComboBox;
import com.geniescode.share.components.textField.TextField;
import com.geniescode.share.components.dateChooser.DateChooserField;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class SignUpFrame extends JFrame {
    private final List<String> genderList = List.of("Male", "Female", "Others");
    private TextField name;
    private TextField surname;
    private DateChooserField dateOfBirth;
    private ComboBox<String> gender;
    private TextField email;
    private Button logIn;
    private Button registration;

    public SignUpFrame() {
        initComponents();
    }

    private void initComponents() {
        Panel background = new Panel();
        JLabel tittle = new JLabel("Registration");
        name = new TextField("Name", Color.green);
        surname = new TextField("Surname", Color.green);
        dateOfBirth = new DateChooserField("Date Of Birth (yyyy-mm-dd)", Color.green);
        gender = new ComboBox<>("Gender", Color.green);
        email = new TextField("Email", Color.green);
        logIn = new Button("LogIn");
        registration = new Button("Registration");

        gender.addModels(genderList);
        gender.setSelectedIndex(-1);

        tittle.setHorizontalAlignment(SwingConstants.CENTER);
        tittle.setFont(tittle.getFont().deriveFont(Font.PLAIN, 25));
        tittle.setForeground(Color.green);

        background.setLayout(new MigLayout("inset 0px, wrap"));
        background.add(new ClosingBar(), "width 100%");
        background.add(tittle, "width 100%, gap bottom 10px");
        background.add(name, "width 60%, gap left 20%");
        background.add(surname, "width 60%, gap left 20%, gap top 5px");
        background.add(dateOfBirth, "width 60%, gap left 20%, gap top 5px");
        background.add(gender, "width 60%, gap left 20%");
        background.add(email, "width 60%, gap left 20%, gap top 5px");
        background.add(registration, "width 30%, height 35px, gap left 35%, gap top 20px");
        background.add(logIn, "width 30%, height 35px, gap left 35%, gap top 6px");
        background.setBackground(Color.white);

        setLayout(new BorderLayout());
        add(background);
        setSize(new Dimension(700, 500));
        setResizable(false);
        setUndecorated(true);
        setFont(new Font("sanserif", Font.PLAIN, 15));
        setLocationRelativeTo(null);
        addController.accept(this);
    }

    public void addButtonListener(SignUpController controller) {
        logIn.addActionListener(controller);
        registration.addActionListener(controller);
    }

    private final Consumer<SignUpFrame> addController = SignUpController::new;

    public String getName() {
        return name.getText();
    }

    public String getSurname() {
        return surname.getText();
    }

    public LocalDate getDateOfBirth() {
        return LocalDate.parse(dateOfBirth.getText());
    }

    public String getGender() {
        return gender.getSelectedIndex() != -1 || gender.getSelectedItem() != null?
                Objects.requireNonNull(gender.getSelectedItem())
                .toString() : null;
    }

    public String getEmail() {
        return email.getText();
    }

    public void clearName() {
        this.name.setText(null);
    }

    public void clearSurname() {
        this.surname.setText(null);
    }

    public void clearDateOfBirth() {
        this.dateOfBirth.setText(null);
    }

    public void clearGender() {
        this.gender.setSelectedIndex(-1);
    }

    public void clearEmail() {
        this.email.setText(null);
    }

    public Button getLogIn() {
        return logIn;
    }

    public Button getRegistration() {
        return registration;
    }
}
