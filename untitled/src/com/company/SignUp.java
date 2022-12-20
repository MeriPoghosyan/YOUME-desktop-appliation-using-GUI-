package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class SignUp extends JFrame implements ActionListener, MouseListener, FocusListener, KeyListener {
    ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/logo.png")));
    ImageIcon friends = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/frieds.jpeg")));
    Image img1 = (friends).getImage().getScaledInstance(friends.getIconWidth() / 5, friends.getIconHeight() / 5, Image.SCALE_SMOOTH);
    JCheckBox showPass = new JCheckBox("show password");
    JPasswordField repeatPass = new JPasswordField("Repeat Password");
    JButton signUpButton = new JButton();
    JButton uploadImage = new JButton("Choose profile picture");
    JTextField userName = new JTextField("Username");
    JTextField name = new JTextField("Name");
    JTextField surname = new JTextField("Surname");
    JTextField facebook = new JTextField("Facebook URL");
    JTextField instagram = new JTextField("Instagram URL");
    JTextField twitter = new JTextField("Twitter URL");
    JTextField whatsApp = new JTextField("WhatsApp number");
    JTextField viber = new JTextField("Viber number");
    JTextField telegram = new JTextField("Telegram number");
    JPasswordField password = new JPasswordField("Create Password");
    JMenuBar menu = new JMenuBar();
    JMenu signInMenu = new JMenu("Log In");
    JMenu signUpMenu = new JMenu("Sign Up");
    JLabel errorMessage = new JLabel();
    JFileChooser profilePic = new JFileChooser();
    UserDAO userDAO = new UserDAO();
    User user = new User();
    Path defaultImg = Paths.get("C:", "Users", "merya", "Desktop", "UFAR", "4th year", "untitled", "src", "com", "company", "profile_pics", "default.png");
    Path to;
    Path from = defaultImg;
    String image;

    boolean passFilled;
    boolean usernameFilled;
    boolean repeatPassFilled;
    boolean nameFilled;
    boolean surnameFilled;

    public SignUp() throws HeadlessException {
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
        leftPanel.setBounds(0, 0, 1024, 800);
        leftPanel.add(forPhoto);
        leftPanel.add(bluePanel);
        add(leftPanel);

        signInMenu.setFont(new Font("none", Font.PLAIN, 20));
        signInMenu.setForeground(new Color(0xC1C1C1));

        signUpMenu.setFont(new Font("none", Font.PLAIN, 20));
        signUpMenu.setForeground(new Color(0x5DC0F9));

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

        JPanel signUpPanel = new JPanel();
        signUpPanel.setBounds(800, 0, 500, 720);
        signUpPanel.setLayout(new GridLayout(17, 1));
        signUpPanel.setOpaque(true);
        signUpPanel.setBackground(new Color(0xFDFDFE));


        JLabel signUpText = new JLabel("<html>" +
                "<font size='72' color='#7D7D7D'><strong>Sign Up</strong></font>" +
                "</html>");
        JLabel descText = new JLabel("<html>" +
                "<font size='3' color='#C1C1C1'>Create an account to view your friends who are online on different social networking sites, in a single integrated environment</font>" + "</html>");

        signUpPanel.add(signUpText);
        signUpPanel.add(descText);

        JPanel uploadPanel = new JPanel();
        uploadPanel.setBackground(new Color(0xFDFDFE));
        uploadImage.setBorderPainted(false);
        uploadImage.setFocusPainted(false);
        uploadImage.setBackground(new Color(0x5DC0F9));
        uploadImage.setForeground(new Color(0xFDFDFE));
        uploadImage.addActionListener(this);
        uploadPanel.add(uploadImage);

        userName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        userName.setFont(new Font("none", Font.PLAIN, 10));
        userName.setForeground(new Color(0xC1C1C1));
        userName.addFocusListener(this);
        userName.addKeyListener(this);
        name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        name.setFont(new Font("none", Font.PLAIN, 10));
        name.setForeground(new Color(0xC1C1C1));
        name.addFocusListener(this);
        name.addKeyListener(this);
        surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        surname.setFont(new Font("none", Font.PLAIN, 10));
        surname.setForeground(new Color(0xC1C1C1));
        surname.addFocusListener(this);
        surname.addKeyListener(this);
        facebook.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        facebook.setFont(new Font("none", Font.PLAIN, 10));
        facebook.setForeground(new Color(0xC1C1C1));
        facebook.addFocusListener(this);
        instagram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        instagram.setFont(new Font("none", Font.PLAIN, 10));
        instagram.setForeground(new Color(0xC1C1C1));
        instagram.addFocusListener(this);
        twitter.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        twitter.setFont(new Font("none", Font.PLAIN, 10));
        twitter.setForeground(new Color(0xC1C1C1));
        twitter.addFocusListener(this);
        whatsApp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        whatsApp.setFont(new Font("none", Font.PLAIN, 10));
        whatsApp.setForeground(new Color(0xC1C1C1));
        whatsApp.addFocusListener(this);
        viber.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        viber.setFont(new Font("none", Font.PLAIN, 10));
        viber.setForeground(new Color(0xC1C1C1));
        viber.addFocusListener(this);
        telegram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        telegram.setFont(new Font("none", Font.PLAIN, 10));
        telegram.setForeground(new Color(0xC1C1C1));
        telegram.addFocusListener(this);
        password.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        password.setFont(new Font("none", Font.PLAIN, 10));
        password.setForeground(new Color(0xC1C1C1));
        password.setEchoChar((char) 0);
        password.addFocusListener(this);
        password.addKeyListener(this);
        repeatPass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        repeatPass.setFont(new Font("none", Font.PLAIN, 10));
        repeatPass.setForeground(new Color(0xC1C1C1));
        repeatPass.setEchoChar((char) 0);
        repeatPass.addFocusListener(this);
        repeatPass.addKeyListener(this);
        showPass.setFont(new Font("none", Font.PLAIN, 10));
        showPass.setBackground(new Color(0xFDFDFE));
        showPass.setFocusPainted(false);
        showPass.setSelected(true);
        showPass.addActionListener(this);

        signUpPanel.add(uploadPanel);
        signUpPanel.add(name);
        signUpPanel.add(surname);
        signUpPanel.add(userName);
        signUpPanel.add(facebook);
        signUpPanel.add(instagram);
        signUpPanel.add(twitter);
        signUpPanel.add(whatsApp);
        signUpPanel.add(viber);
        signUpPanel.add(telegram);
        signUpPanel.add(password);
        signUpPanel.add(repeatPass);
        signUpPanel.add(showPass);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xFDFDFE));

        ImageIcon signUp = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/signUp.png")));
        Image button = (signUp).getImage().getScaledInstance(signUp.getIconWidth() / 2, signUp.getIconHeight() / 2, Image.SCALE_SMOOTH);
        signUp = new ImageIcon(button);
        signUpButton.setEnabled(false);
        signUpButton.setIcon(signUp);
        signUpButton.setBorderPainted(false);
        signUpButton.setFocusPainted(false);
        signUpButton.setContentAreaFilled(false);
        signUpButton.addActionListener(this);
        buttonPanel.add(signUpButton);
        signUpPanel.add(buttonPanel);
        signUpPanel.add(errorMessage);
        add(signUpPanel);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == showPass) {
            if (showPass.isSelected()) {
                repeatPass.setEchoChar((char) 0);
            } else {
                repeatPass.setEchoChar('*');
            }
        }
        if (e.getSource() == uploadImage) {
            int res = profilePic.showSaveDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                from = Paths.get(profilePic.getSelectedFile().getAbsolutePath());
                uploadImage.setText(String.valueOf(from));
            }

        }
        if (e.getSource() == signUpButton) {
            String filename = userName.getText();
            to = Path.of("C:", "Users", "merya", "Desktop", "UFAR", "4th year", "untitled", "src", "com", "company", "profile_pics", filename + ".jpg");
            CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
            };
            try {

                if (from.compareTo(defaultImg) != 0) {
                    Files.copy(from, to, options);
                    image = to.toString().replace("\\", "\\\\");
                    user.setImage_name(image);
                } else {
                    image = defaultImg.toString();
                    image = from.toString().replace("\\", "\\\\");
                    user.setImage_name(image);
                }

                user.setName(name.getText());
                user.setSurname(surname.getText());
                user.setUsername(userName.getText());
                user.setPassword(String.valueOf(password.getPassword()));
                if (facebook.getText().equals("Facebook URL"))
                    user.setFacebook(null);
                else
                    user.setFacebook(facebook.getText());
                if (instagram.getText().equals("Instagram URL"))
                    user.setInstagram(null);
                else
                    user.setInstagram(instagram.getText());
                if (twitter.getText().equals("Twitter URL"))
                    user.setTwitter(null);
                else
                    user.setTwitter(twitter.getText());
                if (whatsApp.getText().equals("WhatsApp number"))
                    user.setWhatsapp(null);
                else
                    user.setWhatsapp(whatsApp.getText());
                        if(telegram.getText().equals("Telegram number"))
                            user.setTelegram(null);
                        else
                user.setTelegram(telegram.getText());
                        if(viber.getText().equals("Viber number"))
                            user.setViber(null);
                        else
                user.setViber(viber.getText());
                userDAO.insertIntoDb(user);
                setVisible(false);
                new LogIn();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == signInMenu) {
            new LogIn();
            setVisible(false);
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
        if (e.getSource() == signInMenu) {
            signInMenu.setForeground(new Color(0x5DC0F9));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == signInMenu) {
            signInMenu.setForeground(new Color(0xC1C1C1));
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == userName) {
            userName.setForeground(new Color(0x5DC0F9));
            userName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == repeatPass) {
            repeatPass.setForeground(new Color(0x5DC0F9));
            repeatPass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == password) {
            password.setEchoChar('*');
        }
        if (e.getSource() == name) {
            name.setForeground(new Color(0x5DC0F9));
            name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == surname) {
            surname.setForeground(new Color(0x5DC0F9));
            surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == facebook) {
            facebook.setForeground(new Color(0x5DC0F9));
            facebook.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == whatsApp) {
            whatsApp.setForeground(new Color(0x5DC0F9));
            whatsApp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == telegram) {
            telegram.setForeground(new Color(0x5DC0F9));
            telegram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == instagram) {
            instagram.setForeground(new Color(0x5DC0F9));
            instagram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == twitter) {
            twitter.setForeground(new Color(0x5DC0F9));
            twitter.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == viber) {
            viber.setForeground(new Color(0x5DC0F9));
            viber.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }


    }

    @Override
    public void focusLost(FocusEvent e) {

        if (e.getSource() == userName) {
            userName.setForeground(new Color(0xC1C1C1));
            userName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == repeatPass) {
            repeatPass.setForeground(new Color(0xC1C1C1));
            repeatPass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == password) {
            password.setEchoChar('*');
        }
        if (e.getSource() == name) {
            name.setForeground(new Color(0xC1C1C1));
            name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == surname) {
            surname.setForeground(new Color(0xC1C1C1));
            surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == facebook) {
            facebook.setForeground(new Color(0xC1C1C1));
            facebook.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == whatsApp) {
            whatsApp.setForeground(new Color(0xC1C1C1));
            whatsApp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == telegram) {
            telegram.setForeground(new Color(0xC1C1C1));
            telegram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == instagram) {
            instagram.setForeground(new Color(0xC1C1C1));
            instagram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == twitter) {
            twitter.setForeground(new Color(0xC1C1C1));
            twitter.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == viber) {
            viber.setForeground(new Color(0xC1C1C1));
            viber.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
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
        if (e.getSource() == userName) {
            String uName = userName.getText();

            try {
                Statement s = MyConnection.connect().createStatement();
                ResultSet rs = s.executeQuery("select * from users where username = '" + uName + "'");
                usernameFilled = !rs.next() && uName.length() > 0;
                if (!usernameFilled)
                    errorMessage.setText("<html>" +
                            "<font size='3' color='red'>The username is already taken. Try anything else</font>" + "</html>");
                else errorMessage.setText("<html>" +
                        "<font size='3' color='red'> </font>" + "</html>");

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (e.getSource() == password) {
            String original_password = String.valueOf(password.getPassword());
            String confirm_password = String.valueOf(repeatPass.getPassword());
            repeatPassFilled = (original_password.length() > 7) && (confirm_password.length() > 7) && (original_password.equals(confirm_password));
            passFilled = original_password.length() > 7;
            if (!repeatPassFilled)
                if (original_password.length() <= 7) {
                    errorMessage.setText("<html>" +
                            "<font size='3' color='red'>The password is too short</font>" + "</html>");
                } else {
                    errorMessage.setText("<html>" +
                            "<font size='3' color='red'>The passwords do not match</font>" + "</html>");
                }
            if (repeatPassFilled) errorMessage.setText("<html>" +
                    "<font size='3' color='red'> </font>" + "</html>");


        }
        if (e.getSource() == name)
            nameFilled = name.getText().length() > 0;
        if (e.getSource() == repeatPass) {
            String original_password = String.valueOf(password.getPassword());
            String confirm_password = String.valueOf(repeatPass.getPassword());

            repeatPassFilled = (original_password.length() > 7) && (confirm_password.length() > 7) && (original_password.equals(confirm_password));
            if (!repeatPassFilled) {
                errorMessage.setText("<html>" +
                        "<font size='3' color='red'>The passwords do not match</font>" + "</html>");
            }

            if (repeatPassFilled) errorMessage.setText("<html>" +
                    "<font size='3' color='red'> </font>" + "</html>");
        }
        if (e.getSource() == surname)
            surnameFilled = surname.getText().length() > 0;

        signUpButton.setEnabled(nameFilled && surnameFilled && usernameFilled && passFilled && repeatPassFilled);
    }
}


