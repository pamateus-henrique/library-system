package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.UserDAO;
import models.UserModel;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Login");

        usernamelabel.setBounds(30,15,100,30);
        passwordlabel.setBounds(30,50,100,30);
        usernamefield.setBounds(110,15,200,30);
        passwordfield.setBounds(110,50,200,30);
        loginbutton.setBounds(130,90,80,25);

        add(usernamefield);
        add(passwordfield);
        add(loginbutton);
        add(usernamelabel);
        add(passwordlabel);

        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    private void login()
    {
        String username = usernamefield.getText();
        String password = passwordfield.getText();

        if(username.isEmpty()) //If username is null
        {
            JOptionPane.showMessageDialog(null,"Please enter username"); //Display dialog box with the message
        }
        else if(password.isEmpty()) //If password is null
        {
            JOptionPane.showMessageDialog(null,"Please enter password"); //Display dialog box with the message
        } else {
            UserDAO userDAO = new UserDAO();
            UserModel user = userDAO.getUserLogin(username, password);
            if (user != null) {
                boolean isAdmin = user.getAdmin();
                if(isAdmin) {
                    AdminMenu.showAdminMenu();
                } else {
                    UserFrame userFrame = new UserFrame();
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found");
            }
        }
    }

    private final JLabel usernamelabel = new JLabel("Username");
    private final JLabel passwordlabel = new JLabel("Password");
    private final JTextField usernamefield = new JTextField();
    private final JPasswordField passwordfield = new JPasswordField();
    private final JButton loginbutton = new JButton("Login");
}
