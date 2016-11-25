package ru.itis.inform;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Kamil Karimov on 25.11.2016.
 */
public class Main extends JFrame {

    private List<User> users;
    private Table table;
    private JTextField textId;
    private JTextField textName;
    private JTextField textSurname;
    private JTextField textAge;
    private JTextField textIsMale;
    private int size;

    Main() {
        users = new ArrayList<>();
        users.add(new User(1, "Po", "Da", 9, true));
        size = users.size();

        JFrame frame = new JFrame("Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        table = new Table(users);

        JTable jTable = new JTable(table);
        frame.add(jTable);

        JScrollPane jScrollPane = new JScrollPane(jTable);
        jTable.setPreferredScrollableViewportSize(new Dimension(100, 100));
        frame.getContentPane().add(jScrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        textId = new JTextField();
        textName = new JTextField();
        textSurname = new JTextField();
        textAge = new JTextField();
        textIsMale = new JTextField();
        panel.add(new JLabel("Id"));
        panel.add(textId);
        panel.add(new JLabel("Name"));
        panel.add(textName);
        panel.add(new JLabel("Surname"));
        panel.add(textSurname);
        panel.add( new JLabel("Age"));
        panel.add(textAge);
        panel.add(new JLabel("Is male"));
        panel.add(textIsMale);

        panel.add(add());
        panel.add(delete());
        frame.getContentPane().add(panel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private JButton add() {
        JButton add = new JButton("add");
        add.addActionListener(e -> {
            String name = textName.getText();
            String surname = textSurname.getText();
            int age = Integer.valueOf(textAge.getText());
            boolean isMale = Boolean.parseBoolean(textIsMale.getText());
            users.add(new User(size + 1, name, surname, age, isMale));
            size++;
            table.fireTableDataChanged();
        });
        return add;
    }

    private JButton delete() {
        JButton delete = new JButton("delete");
        delete.addActionListener(e -> {
            int id = Integer.valueOf(textId.getText());
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == id) {
                    users.remove(i);
                }
            }
            table.fireTableDataChanged();
        });
        return delete;
    }

    public static void main(String[] args) {
        new Main();
    }
}