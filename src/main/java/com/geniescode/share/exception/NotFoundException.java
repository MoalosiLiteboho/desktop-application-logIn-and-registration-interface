package com.geniescode.share.exception;

import javax.swing.*;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message, Boolean showUser) {
        super(message);
        if(showUser)
            JOptionPane.showMessageDialog(null, message);
    }
}
