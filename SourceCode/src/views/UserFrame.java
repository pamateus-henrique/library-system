package views;

import components.TableBooksModel;
import dao.BookDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends JFrame {
    public UserFrame(){
        setTitle("User menu");
        JButton view_books = new JButton("View books");
        view_books.setBounds(20,20,125,25);
        view_books.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                JFrame frame = new JFrame("Books available");
                BookDAO bookDAO = new BookDAO();
                JTable booktable = new JTable();
                booktable.setModel(new TableBooksModel(new BookDAO().getAllBooks()));


                JScrollPane scrollPane = new JScrollPane(booktable);
                frame.add(scrollPane);
                frame.setSize(800,400);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        add(view_books);
        setSize(600,200);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
