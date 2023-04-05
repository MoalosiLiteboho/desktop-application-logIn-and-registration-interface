package com.geniescode.share.components.dateChooser;

import com.geniescode.share.components.textField.TextField;

import java.awt.Color;
import java.time.LocalDate;

public class DateChooserField extends TextField {
    public DateChooserField(String hintText, Color bottomColor) {
        super(hintText, bottomColor);
        initComponents();
    }

    private void initComponents() {
        DateChooser dateChooser = new DateChooser();

        dateChooser.setTextReference(this);
        dateChooser.setDateFormat("yyyy-MM-dd");

        setText(null);
    }

    public LocalDate getDate() {
        return LocalDate.parse(getText());
    }
}
