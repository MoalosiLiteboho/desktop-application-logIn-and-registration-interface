package com.geniescode.share.components.dateChooser;

import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.GoogleMaterialIcon;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public final class DateChooser extends JPanel {
    private JTextField textReference;
    private final String[] MONTH_ENGLISH = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private String dateFormat = "dd-MM-yyyy";
    private int MONTH = 1;
    private int YEAR = 2021;
    private int DAY = 1;
    private int STATUS = 1;   //  1 is day    2 is month  3 is year
    private int startYear;
    private final SelectedDate selectedDate = new SelectedDate();
    private List<EventDateChooser> events;

    public DateChooser() {
        initComponents();
        execute();
    }

    private void initComponents() {
        header = new JPanel();
        Button cmdForward = new Button();
        JLayeredPane header = new JLayeredPane();
        cmdMonth = new Button();
        javax.swing.JLabel lb = new javax.swing.JLabel();
        cmdYear = new Button();
        Button cmdPrevious = new Button();
        slide = new Slider();

        popup = new JPopupMenu(){
            @Override
            protected void paintComponent(Graphics graphics) {
                graphics.setColor(new Color(114, 113, 113));
                graphics.fillRect(0, 0, getWidth(), getHeight());
                graphics.setColor(Color.WHITE);
                graphics.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
            }
        };

        cmdYear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdYear.setForeground(Color.white);
        cmdYear.setText("2018");
        cmdYear.setFocusPainted(false);
        cmdYear.setFont(new java.awt.Font("sanserif", Font.PLAIN, 14));
        cmdYear.setPaintBackground(false);
        cmdYear.addActionListener(this::cmdYearActionPerformed);

        lb.setForeground(new Color(255, 255, 255));
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        lb.setText("-");

        cmdMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdMonth.setForeground(new Color(255, 255, 255));
        cmdMonth.setText("January");
        cmdMonth.setFocusPainted(false);
        cmdMonth.setFont(new java.awt.Font("sanserif", Font.PLAIN, 14)); // NOI18N
        cmdMonth.setPaintBackground(false);
        cmdMonth.addActionListener(this::cmdMonthActionPerformed);

        cmdForward.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdForward.setIcon(new GoogleMaterialIcon(GoogleMaterialDesignIcon.KEYBOARD_ARROW_LEFT, null, Color.white, Color.white, 20).toIcon());
        cmdForward.setFocusable(true);
        cmdForward.setPaintBackground(false);
        cmdForward.addActionListener(this::cmdForwardActionPerformed);


        header.setLayout(new MigLayout("center, flowx, aligny center", "[][][]", ""));
        header.add(cmdMonth);
        header.add(lb);
        header.add(cmdYear);

        cmdPrevious.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdPrevious.setIcon(new GoogleMaterialIcon(GoogleMaterialDesignIcon.KEYBOARD_ARROW_RIGHT, null, Color.white, Color.white, 20).toIcon()); // NOI18N
        cmdPrevious.setFocusable(true);
        cmdPrevious.setPaintBackground(false);
        cmdPrevious.addActionListener(this::cmdPreviousActionPerformed);
        cmdPrevious.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                cmdPreviousKeyPressed(evt);
            }
        });

        slide.setLayout(new BoxLayout(slide, BoxLayout.LINE_AXIS));

        this.header.setLayout(new MigLayout("", "[pref!, grow][198px][pref!, grow]", ""));
        this.header.add(cmdPrevious, "grow");
        this.header.add(header, "growx, width 198!");
        this.header.add(cmdForward, "grow");
        this.header.setBackground(new Color(204, 93, 93));
        this.header.setMaximumSize(new Dimension(262, 40));

        setLayout(new MigLayout("", "[grow]", "[][]"));
        add(this.header, "wrap");
        add(slide, "grow, height 165, wrap");
        setBackground(Color.white);
    }

    private void execute() {
        setForeground(new Color(204, 93, 93));
        events = new ArrayList<>();
        popup.add(this);
        toDay(false);
    }

    public void setTextReference(JTextField txt) {
        this.textReference = txt;
        this.textReference.setEditable(false);
        this.textReference.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (textReference.isEnabled()) {
                    showPopup();
                }
            }
        });
        setText(false, 0);
    }

    private void setText(boolean runEvent, int act) {
        if (textReference != null)
            try {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Date date = df.parse(DAY + "-" + MONTH + "-" + YEAR);
                textReference.setText(new SimpleDateFormat(dateFormat).format(date));
            } catch (ParseException exception) {
                exception.printStackTrace();
            }
        if (runEvent)
            runEvent(act);
    }

    private void runEvent(int act) {
        SelectedAction action = () -> act;
        for (EventDateChooser event : events)
            event.dateSelected(action, selectedDate);
    }

    private Event getEventDay(Dates dates) {
        return (MouseEvent evt, int num) -> {
            dates.clearSelected();
            dates.setSelected(num);
            DAY = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 1);
            if (evt != null && evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt)) {
                popup.setVisible(false);
            }
        };
    }

    private Event getEventMonth() {
        return (MouseEvent evt, int num) -> {
            MONTH = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 2);
            Dates dates = new Dates();
            dates.setForeground(getForeground());
            dates.setEvent(getEventDay(dates));
            dates.showDate(MONTH, YEAR, selectedDate);
            if (slide.slideToDown(dates)) {
                cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
                cmdYear.setText(String.valueOf(YEAR));
                STATUS = 1;
            }
        };
    }

    private Event getEventYear() {
        return (MouseEvent evt, int num) -> {
            YEAR = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 3);
            Months d = new Months();
            d.setEvent(getEventMonth());
            if (slide.slideToDown(d)) {
                cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
                cmdYear.setText(String.valueOf(YEAR));
                STATUS = 2;
            }
        };
    }

    private void toDay(boolean runEvent) {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String toDay = df.format(date);
        DAY = Integer.parseInt(toDay.split("-")[0]);
        MONTH = Integer.parseInt(toDay.split("-")[1]);
        YEAR = Integer.parseInt(toDay.split("-")[2]);
        selectedDate.setDay(DAY);
        selectedDate.setMonth(MONTH);
        selectedDate.setYear(YEAR);
        dates.showDate(MONTH, YEAR, selectedDate);
        slide.slideNon(dates);
        cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
        cmdYear.setText(String.valueOf(YEAR));
        setText(runEvent, 0);
    }

    private void setDateNext() {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.showDate(MONTH, YEAR, selectedDate);
        if (slide.slideToLeft(dates)) {
            cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
            cmdYear.setText(String.valueOf(YEAR));
        }
    }

    private void setDateBack() {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.showDate(MONTH, YEAR, selectedDate);
        if (slide.slideToRight(dates)) {
            cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
            cmdYear.setText(String.valueOf(YEAR));
        }
    }

    private void setYearNext() {
        Years years = new Years();
        years.setEvent(getEventYear());
        startYear = years.next(startYear);
        slide.slideToLeft(years);
    }

    private void setYearBack() {
        if (startYear >= 1000) {
            Years years = new Years();
            years.setEvent(getEventYear());
            startYear = years.back(startYear);
            slide.slideToLeft(years);
        }
    }

    public void showPopup() {
        popup.show(textReference, 0, textReference.getHeight());
    }

    private void cmdPreviousActionPerformed(ActionEvent event) {
        if (STATUS == 1) {   //  Date
            if (MONTH == 1) {
                MONTH = 12;
                YEAR--;
            } else {
                MONTH--;
            }
            setDateBack();
        } else if (STATUS == 3) {    //  Year
            setYearBack();
        } else {
            if (YEAR >= 1000) {
                YEAR--;
                Months months = new Months();
                months.setEvent(getEventMonth());
                slide.slideToLeft(months);
                cmdYear.setText(String.valueOf(YEAR));
            }
        }
    }

    private void cmdForwardActionPerformed(ActionEvent evt) {
        if (STATUS == 1) {   //  Date
            if (MONTH == 12) {
                MONTH = 1;
                YEAR++;
            } else {
                MONTH++;
            }
            setDateNext();
        } else if (STATUS == 3) {    //  Year
            setYearNext();
        } else {
            YEAR++;
            Months months = new Months();
            months.setEvent(getEventMonth());
            slide.slideToLeft(months);
            cmdYear.setText(String.valueOf(YEAR));
        }
    }

    private void cmdMonthActionPerformed(ActionEvent evt) {
        if (STATUS != 2) {
            STATUS = 2;
            Months months = new Months();
            months.setEvent(getEventMonth());
            slide.slideToDown(months);
        } else {
            Dates dates = new Dates();
            dates.setForeground(getForeground());
            dates.setEvent(getEventDay(dates));
            dates.showDate(MONTH, YEAR, selectedDate);
            slide.slideToDown(dates);
            STATUS = 1;
        }
    }

    private void cmdYearActionPerformed(ActionEvent evt) {
        if (STATUS != 3) {
            STATUS = 3;
            Years years = new Years();
            years.setEvent(getEventYear());
            startYear = years.showYear(YEAR);
            slide.slideToDown(years);
        } else {
            Dates dates = new Dates();
            dates.setForeground(getForeground());
            dates.setEvent(getEventDay(dates));
            dates.showDate(MONTH, YEAR, selectedDate);
            slide.slideToDown(dates);
            STATUS = 1;
        }
    }

    private void cmdPreviousKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            Component com = slide.getComponent(0);
            if (com instanceof Dates dates) {
                dates.up();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            Component com = slide.getComponent(0);
            if (com instanceof Dates dates) {
                dates.down();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            Component com = slide.getComponent(0);
            if (com instanceof Dates dates) {
                dates.back();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            Component com = slide.getComponent(0);
            if (com instanceof Dates dates) {
                dates.next();
            }
        }
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public void setForeground(Color color) {
        super.setForeground(color);
        if (header != null) {
            header.setBackground(color);
            toDay(false);
        }
    }

    private Button cmdMonth;
    private Button cmdYear;
    private JPanel header;
    private JPopupMenu popup;
    private Slider slide;
}
