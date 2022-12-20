package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class LogIn extends JFrame implements ActionListener, MouseListener, FocusListener, KeyListener {
    ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/logo.png")));
    ImageIcon friends = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/frieds.jpeg")));
    Image img1 = (friends).getImage().getScaledInstance(friends.getIconWidth() / 5, friends.getIconHeight() / 5, Image.SCALE_SMOOTH);
    JCheckBox showPass = new JCheckBox("show password");
    JPasswordField passwordField = new JPasswordField("Password");
    JButton logInButton = new JButton();
    JTextField userName = new JTextField("Username");
    JMenuBar menu = new JMenuBar();
    JMenu signInMenu = new JMenu("Log In");
    JMenu signUpMenu = new JMenu("Sign Up");
    boolean passFilled;
    JLabel errorMessage = new JLabel();
    boolean usernameFilled;

    public LogIn() throws HeadlessException {
        setTitle("YouMe");

        setSize(1500, 800);
        getContentPane().setBackground(new Color(0xFDFDFE));
        setIconImage(logo.getImage());
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        friends = new ImageIcon(img1);
        JLabel forPhoto = new JLabel(friends);
        Dimension size = forPhoto.getPreferredSize();
        forPhoto.setBounds(0, 100, size.width, size.height);

        JLabel bluePanel = new JLabel();
        bluePanel.setOpaque(true);
        bluePanel.setBackground(new Color(0x5DC0F9));
        bluePanel.setBounds(0, 0, 400, 1000);

        JLayeredPane leftPanel = new JLayeredPane();
        leftPanel.setBounds(0, 0, 1024, 1000);
        leftPanel.add(forPhoto);
        leftPanel.add(bluePanel);
        add(leftPanel);

        signInMenu.setFont(new Font("none", Font.PLAIN, 20));
        signInMenu.setForeground(new Color(0x5DC0F9));

        signUpMenu.setFont(new Font("none", Font.PLAIN, 20));
        signUpMenu.setForeground(new Color(0xC1C1C1));

        signInMenu.addMouseListener(this);
        signUpMenu.addMouseListener(this);

        menu.setBorderPainted(false);
        menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        menu.add(Box.createHorizontalStrut(100));
        menu.add(signUpMenu);
        menu.add(Box.createHorizontalStrut(70));
        menu.add(signInMenu);
        menu.setBackground(new Color(0xFDFDFE));

        setJMenuBar(menu);

        JPanel signInPanel = new JPanel();
        signInPanel.setBounds(800, 100, 500, 600);
        signInPanel.setLayout(new GridLayout(4, 1));
        signInPanel.setOpaque(true);
        signInPanel.setBackground(new Color(0xFDFDFE));


        JLabel logInText = new JLabel("<html>" +
                "<font size='72' color='#7D7D7D'><strong>LOG IN</strong></font>" +
                "</html>");
        JLabel descText = new JLabel("<html>" +
                "<font size='3' color='#C1C1C1'>Sign in to view your friends who are online on different social networking sites, in a single integrated environment</font>" + "</html>");

        JPanel upperWriting = new JPanel(new GridLayout(2, 1));
        upperWriting.setBorder(new EmptyBorder(10, 50, 10, 50));
        upperWriting.setBackground(new Color(0xFDFDFE));
        upperWriting.add(logInText);
        upperWriting.add(descText);

        signInPanel.add(upperWriting);

        JPanel inputFields = new JPanel(new GridLayout(3, 1, 0, 25));
        inputFields.setBorder(new EmptyBorder(0, 50, 0, 50));
        inputFields.setBackground(new Color(0xFDFDFE));


        userName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        userName.setFont(new Font("none", Font.PLAIN, 20));
        userName.setForeground(new Color(0xC1C1C1));
        userName.addFocusListener(this);
        userName.addKeyListener(this);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        passwordField.setFont(new Font("none", Font.PLAIN, 20));
        passwordField.setForeground(new Color(0xC1C1C1));
        passwordField.addFocusListener(this);
        passwordField.addKeyListener(this);
        showPass.setFont(new Font("none", Font.PLAIN, 20));
        showPass.setBackground(new Color(0xFDFDFE));
        showPass.setFocusPainted(false);
        showPass.addActionListener(this);

        inputFields.add(userName);
        inputFields.add(passwordField);
        inputFields.add(showPass);


        signInPanel.add(inputFields);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xFDFDFE));

        ImageIcon signIn = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/signIn.png")));
        Image button = (signIn).getImage().getScaledInstance(signIn.getIconWidth() / 2, signIn.getIconHeight() / 2, Image.SCALE_SMOOTH);
        signIn = new ImageIcon(button);
        logInButton.setIcon(signIn);
        logInButton.setEnabled(false);
        logInButton.setBorderPainted(false);
        logInButton.setFocusPainted(false);
        logInButton.setContentAreaFilled(false);
        logInButton.addActionListener(this);
        buttonPanel.add(logInButton);

        signInPanel.add(buttonPanel);
        signInPanel.add(errorMessage);
        add(signInPanel);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showPass) {
            if (showPass.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
        if (e.getSource() == logInButton) {

                Statement ns;
                try {
                    ns = MyConnection.connect().createStatement();
                    ResultSet ex = ns.executeQuery("select * from users where username = '" + userName.getText() + "'");
                    if(ex.next()&& String.valueOf(passwordField.getPassword()).equals(ex.getString(5))){
                        setVisible(false);
                        new HomePage(userName.getText());

                    }
                    else errorMessage.setText("<html>" +
                            "<font size='3' color='red'>The username or the password is incorrect</font>" + "</html>");
                    ;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() ==signUpMenu){

            setVisible(false);
            new SignUp();

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() ==signUpMenu){
            signUpMenu.setForeground(new Color(0x5DC0F9));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() ==signUpMenu){
            signUpMenu.setForeground(new Color(0xC1C1C1));
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == userName) {
//            if(userName.getText().equals("Username")){
//                userName.setText("");
//            }
            userName.setForeground(new Color(0x5DC0F9));
            userName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == passwordField) {
//            String pass = new String(passwordField.getPassword());
//            if(pass.equals("Password")){
//                passwordField.setText("");
//            }
            passwordField.setForeground(new Color(0x5DC0F9));
            passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource()==userName){
            userName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
            userName.setForeground(new Color(0xC1C1C1));


        }
        if (e.getSource()==passwordField){
            passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
            passwordField.setForeground(new Color(0xC1C1C1));

        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource()==userName)
            usernameFilled = userName.getText().length() > 0;
        if(e.getSource()==passwordField)
            passFilled = passwordField.getPassword().length > 0;
        logInButton.setEnabled(usernameFilled&&passFilled);
    }
}

