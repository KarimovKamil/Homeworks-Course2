package ru.itis.inform;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil Karimov on 25.11.2016.
 */
public class Table extends AbstractTableModel {

    List<User> users;

    public Table(List<User> users) {
        this.users = users;
    }

    public int getRowCount() {
        return users.size();
    }

    public int getColumnCount() {
        return 5;
    }

    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "id";
                break;
            case 1:
                result = "name";
                break;
            case 2:
                result = "surname";
                break;
            case 3:
                result = "age";
                break;
            case 4:
                result = "is male";
                break;
        }
        return result;
    }

    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return users.get(r).getId();
            case 1:
                return users.get(r).getName();
            case 2:
                return users.get(r).getSurname();
            case 3:
                return users.get(r).getAge();
            case 4:
                return users.get(r).isGender();
            default:
                return "";
        }
    }
}