package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public class EditProfile extends JFrame implements ActionListener, MouseListener, FocusListener, KeyListener {
    private String username;
    private String image;
    boolean usernameFilled = true;
    boolean nameFilled = true;
    boolean surnameFilled = true;
    JButton uploadImage = new JButton("Choose profile picture");
    boolean repeatPassFilled = true;
    JLabel errorMessage = new JLabel();
    boolean passFilled = true;
    JFileChooser profilePic = new JFileChooser();
    JButton applyBtn = new JButton();
    JButton discardBtn = new JButton();
    ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/logo.png")));
    JLabel userName1 = new JLabel("Username: ");
    JLabel name1 = new JLabel("Name: ");
    JLabel surname1 = new JLabel("Surname: ");
    JLabel facebook1 = new JLabel("Facebook URL: ");
    JLabel instagram1 = new JLabel("Instagram URL: ");
    JLabel twitter1 = new JLabel("Twitter URL: ");
    JLabel whatsApp1 = new JLabel("WhatsApp number: ");
    JLabel viber1 = new JLabel("Viber number: ");
    JLabel telegram1 = new JLabel("Telegram number: ");
    JLabel oldPass1 = new JLabel("Old Password: ");
    JLabel newPass1 = new JLabel("New Password: ");
    JLabel imageText = new JLabel("Profile picture");
    JTextField userName;
    JTextField name = new JTextField();
    JTextField surname = new JTextField();
    JTextField facebook = new JTextField();
    JTextField instagram = new JTextField();
    JTextField twitter = new JTextField();
    JTextField whatsApp = new JTextField();
    JTextField viber = new JTextField();
    JTextField telegram = new JTextField();
    JTextField oldPass = new JTextField();
    String realPass;
    JPasswordField newPass = new JPasswordField();
    Path defaultImg;
    Path to;
    Path from;
    UserDAO userDAO = new UserDAO();
    User user = new User();
    int id_user;

    String source;
    JLabel backBtn;

    public EditProfile(String username) throws HeadlessException {
        this.username = username;
        userName = new JTextField(username);
        setTitle("YouMe");

        setSize(1500, 800);
        getContentPane().setBackground(new Color(0xFDFDFE));
        setIconImage(logo.getImage());
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Statement ns;
        try {
            ns = MyConnection.connect().createStatement();
            ResultSet ex = null;
            ex = ns.executeQuery("select * from users where username = '" + username + "'");
            ex.next();
            id_user = ex.getInt(1);

            name.setText(ex.getString(2));

            surname.setText(ex.getString(3));

            realPass = ex.getString(5);

            facebook.setText(ex.getString(6));

            instagram.setText(ex.getString(7));

            twitter.setText(ex.getString(8));

            whatsApp.setText(ex.getString(9));

            viber.setText(ex.getString(10));
            telegram.setText(ex.getString(11));

            source = ex.getString(12);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        defaultImg = Paths.get(source);
        from = defaultImg;

        ImageIcon friends = new ImageIcon(source);
        Image img1 = (friends).getImage().getScaledInstance(600, 550, Image.SCALE_DEFAULT);

        friends = new ImageIcon(img1);
        JLabel forPhoto = new JLabel(friends);
        Dimension size = forPhoto.getPreferredSize();
        forPhoto.setBounds(0, 100, size.width, size.height);


        JLabel bluePanel = new JLabel();
        bluePanel.setOpaque(true);
        bluePanel.setBackground(new Color(0x5DC0F9));
        bluePanel.setBounds(0, 0, 400, 1000);

        JLayeredPane leftPanel = new JLayeredPane();
        leftPanel.setBorder(new EmptyBorder(10, 50, 10, 50));

        leftPanel.setBounds(0, 0, 1024, 800);
        leftPanel.add(forPhoto);
        leftPanel.add(bluePanel);
        add(leftPanel);

        imageText.setFont(new Font("none", Font.PLAIN, 20));
        imageText.setForeground(new Color(0xC1C1C1));

        userName1.setFont(new Font("none", Font.PLAIN, 20));
        userName1.setForeground(new Color(0xC1C1C1));
        userName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        userName.setFont(new Font("none", Font.PLAIN, 20));
        userName.setForeground(new Color(0xC1C1C1));
        userName.addKeyListener(this);
        userName.addFocusListener(this);

        name1.setFont(new Font("none", Font.PLAIN, 20));
        name1.setForeground(new Color(0xC1C1C1));
        name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        name.setFont(new Font("none", Font.PLAIN, 20));
        name.setForeground(new Color(0xC1C1C1));
        name.addFocusListener(this);

        surname1.setFont(new Font("none", Font.PLAIN, 20));
        surname1.setForeground(new Color(0xC1C1C1));
        surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        surname.setFont(new Font("none", Font.PLAIN, 20));
        surname.setForeground(new Color(0xC1C1C1));
        surname.setPreferredSize(new Dimension(420, 60));
        surname.addFocusListener(this);

        facebook1.setFont(new Font("none", Font.PLAIN, 20));
        facebook1.setForeground(new Color(0xC1C1C1));
        facebook.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        facebook.setFont(new Font("none", Font.PLAIN, 20));
        facebook.setForeground(new Color(0xC1C1C1));
        facebook.setPreferredSize(new Dimension(420, 60));
        facebook.addFocusListener(this);

        instagram1.setFont(new Font("none", Font.PLAIN, 20));
        instagram1.setForeground(new Color(0xC1C1C1));
        instagram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        instagram.setFont(new Font("none", Font.PLAIN, 20));
        instagram.setForeground(new Color(0xC1C1C1));
        instagram.setPreferredSize(new Dimension(420, 60));
        instagram.addFocusListener(this);

        twitter1.setFont(new Font("none", Font.PLAIN, 20));
        twitter1.setForeground(new Color(0xC1C1C1));
        twitter.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        twitter.setFont(new Font("none", Font.PLAIN, 20));
        twitter.setForeground(new Color(0xC1C1C1));
        twitter.setPreferredSize(new Dimension(420, 60));
        twitter.addFocusListener(this);

        whatsApp1.setFont(new Font("none", Font.PLAIN, 20));
        whatsApp1.setForeground(new Color(0xC1C1C1));
        whatsApp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        whatsApp.setFont(new Font("none", Font.PLAIN, 20));
        whatsApp.setForeground(new Color(0xC1C1C1));
        whatsApp.setPreferredSize(new Dimension(420, 60));
        whatsApp.addFocusListener(this);

        viber1.setFont(new Font("none", Font.PLAIN, 20));
        viber1.setForeground(new Color(0xC1C1C1));
        viber.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        viber.setFont(new Font("none", Font.PLAIN, 20));
        viber.setForeground(new Color(0xC1C1C1));
        viber.setPreferredSize(new Dimension(420, 60));
        viber.addFocusListener(this);

        telegram1.setFont(new Font("none", Font.PLAIN, 20));
        telegram1.setForeground(new Color(0xC1C1C1));
        telegram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        telegram.setFont(new Font("none", Font.PLAIN, 20));
        telegram.setForeground(new Color(0xC1C1C1));
        telegram.setPreferredSize(new Dimension(420, 60));
        telegram.addFocusListener(this);

        oldPass1.setFont(new Font("none", Font.PLAIN, 20));
        oldPass1.setForeground(new Color(0xC1C1C1));
        oldPass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        oldPass.setFont(new Font("none", Font.PLAIN, 20));
        oldPass.setForeground(new Color(0xC1C1C1));
        oldPass.setPreferredSize(new Dimension(420, 60));
        oldPass.addKeyListener(this);
        oldPass.addFocusListener(this);

        newPass1.setFont(new Font("none", Font.PLAIN, 20));
        newPass1.setForeground(new Color(0xC1C1C1));
        newPass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        newPass.setFont(new Font("none", Font.PLAIN, 20));
        newPass.setForeground(new Color(0xC1C1C1));
        newPass.setPreferredSize(new Dimension(420, 60));
        newPass.addKeyListener(this);
        newPass.addFocusListener(this);

        JPanel uploadPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
        uploadPanel.setBackground(new Color(0xFDFDFE));
        uploadImage.setBorderPainted(false);
        uploadImage.setFocusPainted(false);
        uploadImage.setBackground(new Color(0x5DC0F9));
        uploadImage.setForeground(new Color(0xFDFDFE));
        uploadImage.addActionListener(this);
        uploadPanel.add(uploadImage);

        JPanel InfoPanel = new JPanel();
        InfoPanel.setBounds(650, 0, 750, 800);
        InfoPanel.setLayout(new GridLayout(14, 2));
        InfoPanel.setOpaque(true);
        InfoPanel.setBackground(new Color(0xFDFDFE));

        JPanel buttonApply = new JPanel();
        buttonApply.setBackground(new Color(0xFDFDFE));
        ImageIcon apply = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/apply.png")));
        Image button = (apply).getImage().getScaledInstance(apply.getIconWidth() / 2, apply.getIconHeight() / 2, Image.SCALE_SMOOTH);
        apply = new ImageIcon(button);
        applyBtn.setIcon(apply);
        applyBtn.setBorderPainted(false);
        applyBtn.setFocusPainted(false);
        applyBtn.setContentAreaFilled(false);
        applyBtn.addActionListener(this);
        buttonApply.add(applyBtn);

        JPanel buttonDiscard = new JPanel();
        buttonDiscard.setBackground(new Color(0xFDFDFE));
        ImageIcon discard = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/discard.png")));
        Image button1 = (discard).getImage().getScaledInstance(discard.getIconWidth() / 2, discard.getIconHeight() / 2, Image.SCALE_SMOOTH);
        discard = new ImageIcon(button1);
        discardBtn.setIcon(discard);
        discardBtn.setBorderPainted(false);
        discardBtn.setFocusPainted(false);
        discardBtn.setContentAreaFilled(false);
        discardBtn.addActionListener(this);
        buttonDiscard.add(discardBtn);

        InfoPanel.add(imageText);
        InfoPanel.add(uploadPanel);

        InfoPanel.add(userName1);
        InfoPanel.add(userName);

        InfoPanel.add(name1);
        InfoPanel.add(name);

        InfoPanel.add(surname1);
        InfoPanel.add(surname);

        InfoPanel.add(facebook1);
        InfoPanel.add(facebook);

        InfoPanel.add(instagram1);
        InfoPanel.add(instagram);

        InfoPanel.add(twitter1);
        InfoPanel.add(twitter);

        InfoPanel.add(whatsApp1);
        InfoPanel.add(whatsApp);

        InfoPanel.add(viber1);
        InfoPanel.add(viber);

        InfoPanel.add(telegram1);
        InfoPanel.add(telegram);
        InfoPanel.add(oldPass1);
        InfoPanel.add(oldPass);
        InfoPanel.add(newPass1);
        InfoPanel.add(newPass);
        InfoPanel.add(buttonDiscard);
        InfoPanel.add(buttonApply);
        InfoPanel.add(errorMessage);
        add(InfoPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == discardBtn) {
            setVisible(false);
            new ProfilePage(username);
        }
        if (e.getSource() == uploadImage) {
            int res = profilePic.showSaveDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                from = Paths.get(profilePic.getSelectedFile().getAbsolutePath());
                uploadImage.setText(String.valueOf(from));
            }
        }

        if (e.getSource() == applyBtn) {
            if (oldPass.getText().equals("") || oldPass.getText().equals(realPass)) {
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
                    user.setUser_id(id_user);
                    user.setName(name.getText());
                    user.setSurname(surname.getText());
                    user.setUsername(userName.getText());
                    if (String.valueOf(newPass.getPassword()).equals(""))
                        user.setPassword(realPass);
                    else
                        user.setPassword(String.valueOf(newPass.getPassword()));
                    if (facebook.getText().equals(""))
                        user.setFacebook(null);
                    else
                        user.setFacebook(facebook.getText());
                    if (instagram.getText().equals(""))
                        user.setInstagram(null);
                    else
                        user.setInstagram(instagram.getText());
                    if (twitter.getText().equals(""))
                        user.setTwitter(null);
                    else
                        user.setTwitter(twitter.getText());
                    if (whatsApp.getText().equals(""))
                        user.setWhatsapp(null);
                    else
                        user.setWhatsapp(whatsApp.getText());
                    if (telegram.getText().equals(""))
                        user.setTelegram(null);
                    else
                        user.setTelegram(telegram.getText());
                    if (viber.getText().equals(""))
                        user.setViber(null);
                    else
                        user.setViber(viber.getText());

                    userDAO.ChangeInDB(user);
                    setVisible(false);
                    new ProfilePage(username);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else
                showMessageDialog(null, "Your password is incorrect");
            //System.out.println(false);

        }

    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == userName) {
            userName.setForeground(new Color(0x5DC0F9));
            userName1.setForeground(new Color(0x5DC0F9));
            userName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == oldPass) {
            oldPass.setForeground(new Color(0x5DC0F9));
            oldPass1.setForeground(new Color(0x5DC0F9));
            oldPass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == newPass) {
            newPass.setForeground(new Color(0x5DC0F9));
            newPass1.setForeground(new Color(0x5DC0F9));
            newPass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }

        if (e.getSource() == name) {
            name.setForeground(new Color(0x5DC0F9));
            name1.setForeground(new Color(0x5DC0F9));
            name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == surname) {
            surname.setForeground(new Color(0x5DC0F9));
            surname1.setForeground(new Color(0x5DC0F9));
            surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == facebook) {
            facebook.setForeground(new Color(0x5DC0F9));
            facebook1.setForeground(new Color(0x5DC0F9));
            facebook.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == whatsApp) {
            whatsApp.setForeground(new Color(0x5DC0F9));
            whatsApp1.setForeground(new Color(0x5DC0F9));
            whatsApp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == telegram) {
            telegram.setForeground(new Color(0x5DC0F9));
            telegram1.setForeground(new Color(0x5DC0F9));
            telegram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == instagram) {
            instagram.setForeground(new Color(0x5DC0F9));
            instagram1.setForeground(new Color(0x5DC0F9));
            instagram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == twitter) {
            twitter.setForeground(new Color(0x5DC0F9));
            twitter1.setForeground(new Color(0x5DC0F9));
            twitter.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }
        if (e.getSource() == viber) {
            viber.setForeground(new Color(0x5DC0F9));
            viber1.setForeground(new Color(0x5DC0F9));
            viber.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == userName) {
            userName.setForeground(new Color(0xC1C1C1));
            userName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
            userName1.setForeground(new Color(0xC1C1C1));
        }
        if (e.getSource() == oldPass) {
            oldPass.setForeground(new Color(0xC1C1C1));
            oldPass1.setForeground(new Color(0xC1C1C1));
            oldPass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == newPass) {
            newPass.setForeground(new Color(0xC1C1C1));
            newPass1.setForeground(new Color(0xC1C1C1));
            newPass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }

        if (e.getSource() == name) {
            name.setForeground(new Color(0xC1C1C1));
            name1.setForeground(new Color(0xC1C1C1));
            name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == surname) {
            surname.setForeground(new Color(0xC1C1C1));
            surname1.setForeground(new Color(0xC1C1C1));
            surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == facebook) {
            facebook.setForeground(new Color(0xC1C1C1));
            facebook1.setForeground(new Color(0xC1C1C1));
            facebook.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == whatsApp) {
            whatsApp.setForeground(new Color(0xC1C1C1));
            whatsApp1.setForeground(new Color(0xC1C1C1));
            whatsApp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == telegram) {
            telegram.setForeground(new Color(0xC1C1C1));
            telegram1.setForeground(new Color(0xC1C1C1));
            telegram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == instagram) {
            instagram.setForeground(new Color(0xC1C1C1));
            instagram1.setForeground(new Color(0xC1C1C1));
            instagram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == twitter) {
            twitter.setForeground(new Color(0xC1C1C1));
            twitter1.setForeground(new Color(0xC1C1C1));
            twitter.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        }
        if (e.getSource() == viber) {
            viber.setForeground(new Color(0xC1C1C1));
            viber1.setForeground(new Color(0xC1C1C1));
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
                ResultSet rs = s.executeQuery("select * from users where username = '" + uName + "' and username != '" + username + "'");
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
        if (e.getSource() == oldPass) {
            String original_password = oldPass.getText();
            String confirm_password = String.valueOf(newPass.getPassword());
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
        if (e.getSource() == newPass) {
            String original_password = String.valueOf(newPass.getPassword());
            String confirm_password = String.valueOf(newPass.getPassword());

            repeatPassFilled = (original_password.length() > 7) && (confirm_password.length() > 7) && (original_password.equals(confirm_password));
            if (!repeatPassFilled) {
                errorMessage.setText("<html>" +
                        "<font size='3' color='red'>The passwords do not match</font>" + "</html>");
            }

            if (repeatPassFilled) errorMessage.setText("<html>" +
                    "<font size='3' color='red'> </font>" + "</html>");
        }
        if (e.getSource() == name)
            nameFilled = name.getText().length() > 0;
        if (e.getSource() == surname)
            surnameFilled = surname.getText().length() > 0;
        applyBtn.setEnabled(repeatPassFilled && passFilled && usernameFilled && nameFilled && surnameFilled);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == backBtn) {
            setVisible(false);
            new HomePage(username);
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


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
