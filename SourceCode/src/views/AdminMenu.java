package views;
import javax.swing.*;

import components.TableBooksModel;
import components.TableIssuesModel;
import components.TableUserModel;
import dao.*;
import models.BookModel;
import models.UserModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu {
    public static void showAdminMenu() {
        JFrame mainframe = new JFrame("Admin Functions");

        //menu for view books
        JButton view_books = new JButton("View books");
        view_books.setBounds(20,20,125,25);
        view_books.addActionListener(e -> {
            JFrame frame1 = new JFrame("Books available");
            JTable booktable = new JTable();
            booktable.setModel(new TableBooksModel(new BookDAO().getAllBooks()));


            JScrollPane scrollPane = new JScrollPane(booktable);
            frame1.add(scrollPane);
            frame1.setSize(800,400);
            frame1.setVisible(true);
            frame1.setLocationRelativeTo(null);
        });


        //menu for view users
        JButton view_users = new JButton("View Users");
        view_users.setBounds(280,55,160,25);
        view_users.addActionListener(e -> {
            JFrame frame12 = new JFrame("User list");
            JTable usertable = new JTable();
            usertable.setModel(new TableUserModel(new UserDAO().ListUsers()));

            JScrollPane scrollPane = new JScrollPane(usertable);
            frame12.add(scrollPane);
            frame12.setSize(800,400);
            frame12.setVisible(true);
            frame12.setLocationRelativeTo(null);

        });

        //menu for issue a book
        JButton issue_book = new JButton("Issue Book");
        issue_book.setBounds(150,90,125,25);
        issue_book.addActionListener(e -> {
            JFrame frame13 = new JFrame("Enter book and user details");
            JLabel l1,l2;
            l1 = new JLabel("BID");
            l1.setBounds(30,15,100,30);
            l2 = new JLabel("UID");
            l2.setBounds(30,50,100,30);

            JTextField bid_field = new JTextField();
            bid_field.setBounds(110,15,200,30);

            JTextField uid_field = new JTextField();
            uid_field.setBounds(110,50,200,30);
            JButton issue = new JButton("Issue book");
            issue.setBounds(130,130,120,25);
            issue.addActionListener(e1 -> {
                String uid = uid_field.getText();
                String bid = bid_field.getText();
                JOptionPane.showMessageDialog(null,(new IssueDAO().issueBook(uid,bid)));
            });
            frame13.add(l1);
            frame13.add(l2);
            frame13.add(uid_field);
            frame13.add(bid_field);
            frame13.add(issue);
            frame13.setSize(350,200);
            frame13.setLayout(null);
            frame13.setVisible(true);
            frame13.setLocationRelativeTo(null);
        });


        //menu for add a user
        JButton add_user = new JButton("Add user");
        add_user.setBounds(450,20,120,25);
        add_user.addActionListener(e -> {
            JFrame frame14 = new JFrame("Enter the user details");

            //creating labels
            JLabel usernamelabel,passwordlabel,namelabel,adminlabel,teacherlabel;
            usernamelabel = new JLabel("Username");
            passwordlabel = new JLabel("Password");
            namelabel = new JLabel("Name");
            adminlabel = new JLabel("Admin");
            teacherlabel = new JLabel("Teacher");

            //setting bounds for labels
            usernamelabel.setBounds(30,15,100,30);
            passwordlabel.setBounds(30,50,100,30);
            namelabel.setBounds(30,85,100,30);
            adminlabel.setBounds(30,120,100,30);
            teacherlabel.setBounds(30,155,100,30);

            //creating fields
            JTextField username_field = new JTextField();
            JPasswordField password_field = new JPasswordField();
            JTextField name_field = new JTextField();
            JRadioButton admin_field = new JRadioButton("Admin");
            JRadioButton teacher_field = new JRadioButton("Teacher");

            //setting bounds for fields
            username_field.setBounds(110,15,100,30);
            password_field.setBounds(110,50,100,30);
            name_field.setBounds(110,85,100,30);
            admin_field.setBounds(110,120,100,30);
            teacher_field.setBounds(110,155,100,30);

            JButton create = new JButton("Create user");
            create.setBounds(130,190,120,25);
            create.addActionListener(e12 -> {

                String username = username_field.getText();
                String password = password_field.getText();
                String name = name_field.getText();
                Boolean admin = admin_field.isSelected();
                Boolean teacher = teacher_field.isSelected();
                UserModel userModel = new UserModel(username,password,name,admin,teacher);
                new UserDAO().CreateUser(userModel);
            });

            frame14.add(usernamelabel);
            frame14.add(passwordlabel);
            frame14.add(namelabel);
            frame14.add(adminlabel);
            frame14.add(teacherlabel);
            frame14.add(username_field);
            frame14.add(password_field);
            frame14.add(name_field);
            frame14.add(admin_field);
            frame14.add(teacher_field);
            frame14.add(create);
            frame14.setSize(350,350);
            frame14.setLayout(null);
            frame14.setVisible(true);
            frame14.setLocationRelativeTo(null);


        });

        //menu for delete a user
        JButton delete_user = new JButton("Delete user");
        delete_user.setBounds(20,55,125,25);
        delete_user.addActionListener(e -> {
            JFrame frame15 = new JFrame("Enter the user details");

            //creating labels
            JLabel uid_label = new JLabel("UID");
            uid_label.setBounds(30,15,100,30);

            //creating fields
            JTextField uid_field = new JTextField();
            uid_field.setBounds(100,15,100,30);

            JButton delete = new JButton("Delete user");
            delete.setBounds(130,100,120,25);
            delete.addActionListener(e13 -> {
                int uid = Integer.parseInt(uid_field.getText());
                JOptionPane.showMessageDialog(null,new UserDAO().DeleteUser(uid));
            });
            frame15.add(uid_label);
            frame15.add(uid_field);
            frame15.add(delete);
            frame15.setSize(350,200);
            frame15.setLayout(null);
            frame15.setVisible(true);
            frame15.setLocationRelativeTo(null);
        });

        //menu for return a book
        JButton return_book = new JButton("Return book");
        return_book.setBounds(150,55,125,25);
        return_book.addActionListener(e -> {
            JFrame frame16 = new JFrame("Enter the issue ISD");

            //creating labels
            JLabel isid_label = new JLabel("ISD");

            //setting bounds for bid
            isid_label.setBounds(30,15,100,30);

            //creating fields
            JTextField isid_field = new JTextField();

            //setting bounds for fields
            isid_field.setBounds(110,15,100,30);

            JButton delete = new JButton("Return book");
            delete.setBounds(130,150,120,25);
            delete.addActionListener(e14 -> {
                int isid = Integer.parseInt(isid_field.getText());

                JOptionPane.showMessageDialog(null,new IssueDAO().returnBook(isid));
            });
        frame16.add(isid_field);
        frame16.add(isid_label);
        frame16.add(delete);
        frame16.setSize(350,200);
        frame16.setLayout(null);
        frame16.setVisible(true);
        frame16.setLocationRelativeTo(null);
        });


        //menu for create a book
        JButton add_book = new JButton("Add book");
        add_book.setBounds(150,20,125,25);
        add_book.addActionListener(e -> {
            JFrame frame17 = new JFrame("Enter the book details");

            //creating labels
            JLabel title_label = new JLabel("Title");
            JLabel author_label = new JLabel("Author");
            JLabel edition_label = new JLabel("Edition");
            JLabel publisher_label = new JLabel("Publisher");
            JLabel year_label = new JLabel("Year");
            JLabel isbn_label = new JLabel("ISBN");

            //setting bounds for labels
            title_label.setBounds(30,15,100,30);
            author_label.setBounds(30,50,100,30);
            edition_label.setBounds(30,85,100,30);
            publisher_label.setBounds(30,120,100,30);
            year_label.setBounds(30,155,100,30);
            isbn_label.setBounds(30,190,100,30);

            //creating fields
            JTextField title_field = new JTextField();
            JTextField author_field = new JTextField();
            JTextField edition_field = new JTextField();
            JTextField publisher_field = new JTextField();
            JTextField year_field = new JTextField();
            JTextField isbn_field = new JTextField();

            //setting bounds for fields
            title_field.setBounds(130,15,100,30);
            author_field.setBounds(130,50,100,30);
            edition_field.setBounds(130,85,100,30);
            publisher_field.setBounds(130,120,100,30);
            year_field.setBounds(130,155,100,30);
            isbn_field.setBounds(130,190,100,30);


            JButton add = new JButton("Add book");
            add.setBounds(130,250,120,25);
            add.addActionListener(e15 -> {
                String title = title_field.getText();
                String author = author_field.getText();
                int edition = Integer.parseInt(edition_field.getText());
                String publisher = publisher_field.getText();
                int year = Integer.parseInt(year_field.getText());
                String isbn = isbn_field.getText();
                BookModel book = new BookModel(title,author,edition,publisher,year,isbn);
                new BookDAO().createBook(book);
            });

            frame17.add(title_field);
            frame17.add(author_field);
            frame17.add(edition_field);
            frame17.add(publisher_field);
            frame17.add(year_field);
            frame17.add(isbn_field);
            frame17.add(title_label);
            frame17.add(author_label);
            frame17.add(edition_label);
            frame17.add(publisher_label);
            frame17.add(year_label);
            frame17.add(isbn_label);
            frame17.add(add);
            frame17.setSize(400,700);
            frame17.setLayout(null);
            frame17.setVisible(true);
            frame17.setLocationRelativeTo(null);

        });


        //find a user by part of it's name
        JButton finduser = new JButton("Find user");
        finduser.setBounds(20,90,125,25);
        finduser.addActionListener(e -> {
            JFrame frame18 = new JFrame("Find user");

            //setting labels
            JLabel namelabel = new JLabel("Name");
            JLabel usernamelabel = new JLabel("Username");
            namelabel.setBounds(20,15,100,30);
            usernamelabel.setBounds(20,50,100,30);

            //setting fields
            JTextField namefield = new JTextField();
            JTextField usernamefield = new JTextField();
            namefield.setBounds(130,15,100,30);
            usernamefield.setBounds(130,50,100,30);

            JButton search = new JButton("Search");
            search.setBounds(150,85,100,30);
            search.addActionListener(e16 -> {
                String username = usernamefield.getText();
                String name = namefield.getText();
                JFrame frame181 = new JFrame("Users");
                JTable usertable = new JTable();
                JScrollPane scrollPane = new JScrollPane(usertable);

                if(!username.isEmpty() && !name.isEmpty()){
                    JOptionPane.showMessageDialog(null,"You can only seach using one parameter");
                } else if(!name.isEmpty()){
                    usertable.setModel(new TableUserModel(new UserDAO().findUserByName(name,null)));
                } else if(!username.isEmpty()){
                    usertable.setModel(new TableUserModel(new UserDAO().findUserByName(null,username)));
                } else {
                    JOptionPane.showMessageDialog(null,"Fill at least one parameter");
                }

                frame181.add(scrollPane);
                frame181.setSize(800,400);
                frame181.setVisible(true);
                frame181.setLocationRelativeTo(null);

            });
            frame18.add(namefield);
            frame18.add(usernamefield);
            frame18.add(namelabel);
            frame18.add(usernamelabel);
            frame18.add(search);
            frame18.setSize(350,200);
            frame18.setLayout(null);
            frame18.setVisible(true);
            frame18.setLocationRelativeTo(null);
        });

        //menu to delete a book
        JButton deletebook = new JButton("Delete book");
        deletebook.setBounds(280,20,160,25);
        deletebook.addActionListener(e -> {
            JFrame frame19 = new JFrame("Delete book");

            //setting labels
            JLabel bidlabel = new JLabel("BID");
            bidlabel.setBounds(20,15,125,25);

            //setting fields
            JTextField bidfield = new JTextField();
            bidfield.setBounds(150,15,125,25);

            JButton delete = new JButton("Delete");
            delete.setBounds(150,85,100,30);
            delete.addActionListener(e17 -> {
                int uid = Integer.parseInt(bidfield.getText());
                new BookDAO().deleteBook(uid);
                JOptionPane.showMessageDialog(null,"Book deleted");
            });
            frame19.add(bidfield);
            frame19.add(bidlabel);
            frame19.add(delete);
            frame19.setSize(350,150);
            frame19.setLayout(null);
            frame19.setVisible(true);
            frame19.setLocationRelativeTo(null);
        });


        //find a user by part of it's name
        JButton findbook = new JButton("Find book");
        findbook.setBounds(280,90,155,25);
        findbook.addActionListener(e -> {
            JFrame frame20 = new JFrame("Find user");

            //setting labels
            JLabel titlelabel = new JLabel("Title");
            JLabel publisherlabel = new JLabel("Publisher");
            JLabel isbnlabel = new JLabel("ISBN");
            titlelabel.setBounds(20,15,100,30);
            publisherlabel.setBounds(20,50,100,30);
            isbnlabel.setBounds(20,85,100,30);

            //setting fields
            JTextField titlefield = new JTextField();
            JTextField publisherfield = new JTextField();
            JTextField isbnfield = new JTextField();
            titlefield.setBounds(130,15,100,30);
            publisherfield.setBounds(130,50,100,30);
            isbnfield.setBounds(130,85,100,30);


            JButton search = new JButton("Search");
            search.setBounds(150,120,100,30);
            search.addActionListener(e18 -> {
                String title = titlefield.getText();
                String publisher = publisherfield.getText();
                String isbn = isbnfield.getText();
                JFrame frame201 = new JFrame("Books");
                JTable bookstable = new JTable();
                JScrollPane scrollPane = new JScrollPane(bookstable);

                if(title.isEmpty() && publisher.isEmpty() && isbn.isEmpty()){
                    JOptionPane.showMessageDialog(null,"You can only seach using one parameter");
                } else if(!title.isEmpty()){
                    bookstable.setModel(new TableBooksModel(new BookDAO().findBook(title,null,null)));
                } else if(!publisher.isEmpty()){
                    bookstable.setModel(new TableBooksModel(new BookDAO().findBook(null,publisher,null)));
                } else if(!isbn.isEmpty()){
                    bookstable.setModel(new TableBooksModel(new BookDAO().findBook(null,null,isbn)));
                }

                frame201.add(scrollPane);
                frame201.setSize(800,400);
                frame201.setVisible(true);
                frame201.setLocationRelativeTo(null);

            });
            frame20.add(titlefield);
            frame20.add(publisherfield);
            frame20.add(isbnfield);
            frame20.add(titlelabel);
            frame20.add(publisherlabel);
            frame20.add(isbnlabel);
            frame20.add(search);
            frame20.setSize(350,200);
            frame20.setLayout(null);
            frame20.setVisible(true);
            frame20.setLocationRelativeTo(null);
        });

        //menu for reserve a book
        JButton reservebook = new JButton("Reserve book");
        reservebook.setBounds(450,90,125,25);
        reservebook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame21 = new JFrame("Reserve book");
                JLabel uidlabel = new JLabel("UID");
                JLabel bidlabel = new JLabel("BID");
                uidlabel.setBounds(30,15,100,30);
                bidlabel.setBounds(30,50,100,30);


                JTextField uidfield = new JTextField();
                JTextField bidfield = new JTextField();
                uidfield.setBounds(130,15,100,30);
                bidfield.setBounds(130,50,100,30);

                JButton reserve = new JButton("Create Reserve");
                reserve.setBounds(20,85,145,25);
                reserve.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int uid = Integer.parseInt(uidfield.getText());
                        int bid = Integer.parseInt(bidfield.getText());
                        JOptionPane.showMessageDialog(null,new ReservationDAO().creteReservation(uid,bid));
                    }
                });

                JButton cancelreserve = new JButton("Cancel Reserve");
                cancelreserve.setBounds(170,85,150,25);
                cancelreserve.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int uid = Integer.parseInt(uidfield.getText());
                        int bid = Integer.parseInt(bidfield.getText());
                        JOptionPane.showMessageDialog(null,new ReservationDAO().cancelReservation(uid,bid));
                    }
                });

                frame21.add(uidlabel);
                frame21.add(bidlabel);
                frame21.add(uidfield);
                frame21.add(bidfield);
                frame21.add(reserve);
                frame21.add(cancelreserve);
                frame21.setSize(350,200);
                frame21.setLayout(null);
                frame21.setVisible(true);
                frame21.setLocationRelativeTo(null);

            }
        });

        JButton payfine = new JButton("Pay fine");
        payfine.setBounds(20,125,125,25);
        payfine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame22 = new JFrame("Pay fine");

                JLabel uidlabel = new JLabel("UID");
                uidlabel.setBounds(20,15,100,30);

                JTextField uidfield = new JTextField();
                uidfield.setBounds(125,15,100,30);
                JButton pay = new JButton("Pay");
                pay.setBounds(75,55,100,30);
                pay.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int uid = Integer.parseInt(uidfield.getText());
                        JOptionPane.showMessageDialog(null,new FineDAO().payfine(uid));
                    }
                });
                frame22.add(uidlabel);
                frame22.add(uidfield);
                frame22.add(pay);
                frame22.setSize(350,200);
                frame22.setLayout(null);
                frame22.setVisible(true);
                frame22.setLocationRelativeTo(null);
            }
        });

        //menu for view issues
        JButton viewissues = new JButton("View issues");
        viewissues.setBounds(150,125,125,25);
        viewissues.addActionListener(e -> {
            JFrame frame23 = new JFrame("Issues");
            JTable issuestable = new JTable();
            issuestable.setModel(new TableIssuesModel(new IssueDAO().getAllIssues()));


            JScrollPane scrollPane = new JScrollPane(issuestable);
            frame23.add(scrollPane);
            frame23.setSize(800,400);
            frame23.setVisible(true);
            frame23.setLocationRelativeTo(null);
        });

        JButton renewissue = new JButton("Renew Reserve");
        renewissue.setBounds(280,125,200,25);
        renewissue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame24 = new JFrame("Renew reservation");

                JLabel isidlabel = new JLabel("ISID");
                isidlabel.setBounds(20,15,100,30);

                JTextField isidfield = new JTextField();
                isidfield.setBounds(125,15,100,30);

                JButton renewbutton = new JButton("Renew");
                renewbutton.setBounds(150,50,100,30);
                renewbutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int isid = Integer.parseInt(isidfield.getText());
                        JOptionPane.showMessageDialog(null,new IssueDAO().renewissue(isid));
                    }
                });
                frame24.add(isidfield);
                frame24.add(isidlabel);
                frame24.add(renewbutton);
                frame24.setSize(350,200);
                frame24.setLayout(null);
                frame24.setVisible(true);
                frame24.setLocationRelativeTo(null);
            }
        });

        mainframe.add(view_books);
        mainframe.add(view_users);
        mainframe.add(issue_book);
        mainframe.add(add_user);
        mainframe.add(delete_user);
        mainframe.add(return_book);
        mainframe.add(add_book);
        mainframe.add(finduser);
        mainframe.add(deletebook);
        mainframe.add(findbook);
        mainframe.add(reservebook);
        mainframe.add(payfine);
        mainframe.add(viewissues);
        mainframe.add(renewissue);
        mainframe.setSize(600,200);
        mainframe.setLayout(null);
        mainframe.setVisible(true);
        mainframe.setLocationRelativeTo(null);
    }
}
