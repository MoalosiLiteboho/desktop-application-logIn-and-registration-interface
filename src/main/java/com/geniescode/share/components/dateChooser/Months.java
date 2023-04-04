package com.geniescode.share.components.dateChooser;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.util.stream.IntStream;

public final class Months extends JPanel {
    private final List<String> MONTH_ENGLISH = List.of("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    private Event event;

    public Months() {
        initComponents();
    }

    private void addEvent() {
        for (int index = 0; index < getComponentCount(); index++)
            ((Button) getComponent(index)).setEvent(event);
    }

    private void initComponents() {
        setLayout(new GridLayout(4, 4));
        addMonths();
        setBackground(Color.white);
    }

    private void addMonths() {
        IntStream.range(0, MONTH_ENGLISH.size())
                .forEach(index -> {
                    String monthName = MONTH_ENGLISH.get(index);
                    Button monthButton = new Button();
                    monthButton.setBackground(Color.white);
                    monthButton.setForeground(Color.gray);
                    monthButton.setText(monthName);
                    monthButton.setName(String.valueOf(index + 1));
                    monthButton.setOpaque(true);
                    add(monthButton);
                });
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }
}
