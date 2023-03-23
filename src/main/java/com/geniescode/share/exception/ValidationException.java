package com.geniescode.share.exception;

import javax.swing.*;

public class ValidationException extends RuntimeException{
    public ValidationException(String message, Boolean showUser) {
        super(message);
        if(showUser)
            JOptionPane.showMessageDialog(null, message);
    }
}
