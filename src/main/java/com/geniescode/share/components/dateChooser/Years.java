package com.geniescode.share.components.dateChooser;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;

public final class Years extends javax.swing.JPanel {

    private Event event;
    private int startYear;

    public Years() {
        initComponents();
    }

    public int showYear(int year) {
        year = calculateYear(year);
        for (int index = 0; index < getComponentCount(); index++) {
            ((JButton) getComponent(index)).setText(String.valueOf(year++));
        }
        return startYear;
    }

    private int calculateYear(int year) {
        year -= year % 10;
        startYear = year;
        return year;
    }

    private void addEvent() {
        for (int i = 0; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    private void initComponents() {
        setLayout(new GridLayout(5, 4));
        addYearsButton();
        setBackground(Color.white);
    }

    private void addYearsButton() {
        for (int index = 2010; index < 2030; index++) {
            Button yearButton = new Button();
            yearButton.setBackground(Color.white);
            yearButton.setForeground(Color.gray);
            yearButton.setText(String.valueOf(index));
            yearButton.setName("year");
            yearButton.setOpaque(true);
            add(yearButton);
        }
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    public int next(int year) {
        showYear(year + 20);
        return startYear;
    }

    public int back(int year) {
        showYear(year - 20);
        return startYear;
    }
}
