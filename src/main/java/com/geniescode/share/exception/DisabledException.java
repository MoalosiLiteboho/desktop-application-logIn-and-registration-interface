package com.geniescode.share.exception;

import javax.swing.*;

public class DisabledException extends RuntimeException{
    public DisabledException(String message, Boolean showUser) {
        super(message);
        if(showUser)
            JOptionPane.showMessageDialog(null, message);
    }
}
