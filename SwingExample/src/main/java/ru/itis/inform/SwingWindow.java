package ru.itis.inform;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kamil Karimov on 16.11.2016.
 */
public class SwingWindow extends JFrame {
    private JPanel centralPanel = new JPanel();
    private JLabel text = new JLabel();
    private int buttonWidth = 150;
    private int buttonHeight = 40;
    private SiteParser siteParser = new SiteParser();

    public SwingWindow() {
        super("Swing Window");
        JToolBar toolBar = new JToolBar();
        JMenuBar rightButtons = new JMenuBar();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(200, 200);
        setSize(500, 400);
        setLayout(new BorderLayout());

        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
        toolBar.add(buttonExit());
        toolBar.add(buttonAbout());

        rightButtons.setLayout(new BoxLayout(rightButtons, BoxLayout.Y_AXIS));
        rightButtons.add(buttonShowRed());
        rightButtons.add(Box.createVerticalStrut(5));
        rightButtons.add(buttonShowTime());
        rightButtons.add(Box.createVerticalStrut(5));
        rightButtons.add(buttonShowWeather());

        centralPanel.setSize(250, 250);
        centralPanel.setBackground(Color.WHITE);
        text.setText("Central panel");
        centralPanel.add(text);

        getContentPane().add(centralPanel, BorderLayout.CENTER);
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(rightButtons, BorderLayout.EAST);
    }

    private JButton buttonAbout() {
        JButton about = new JButton("About");
        about.addActionListener(e -> JOptionPane.showMessageDialog(centralPanel, "Information"));
        return about;
    }

    private JButton buttonExit() {
        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> {
            setVisible(false);
            System.exit(0);
        });
        return exit;
    }

    private JButton buttonShowRed() {
        JButton showRed = new JButton("Show Red");
        showRed.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        showRed.addActionListener(e -> {
            if (centralPanel.getBackground().equals(Color.red)) {
                centralPanel.setBackground(Color.WHITE);
            } else {
                centralPanel.setBackground(Color.red);
            }
        });
        return showRed;
    }

    private JButton buttonShowTime() {
        JButton showTime = new JButton("Show Time");
        showTime.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        showTime.addActionListener(e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            text.setText(sdf.format(new Date()));
        });
        return showTime;
    }

    private JButton buttonShowWeather() {
        JButton showWeather = new JButton("Show Weather");
        showWeather.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        showWeather.addActionListener(e -> JOptionPane.showMessageDialog(centralPanel, siteParser.showWeather()));
        return showWeather;
    }
}
