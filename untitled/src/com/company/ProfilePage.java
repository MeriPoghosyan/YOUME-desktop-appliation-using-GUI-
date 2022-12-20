package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class ProfilePage extends JFrame implements ActionListener, MouseListener, FocusListener, KeyListener {

    private String username;
    ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/logo.png")));

    JLabel name = new JLabel();
    JLabel surname = new JLabel();
    JLabel facebook = new JLabel();
    JLabel instagram = new JLabel();
    JLabel twitter = new JLabel();
    JLabel whatsApp = new JLabel();
    JLabel viber = new JLabel();
    JLabel telegram = new JLabel();
    String source;
    JMenuBar menu = new JMenuBar();
    JMenu homeMenu = new JMenu("Home");
    JMenu profileMenu = new JMenu("Profile");
    ImageIcon edit = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/edit.png")));
    JLabel editBtn;
    ImageIcon logout = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/logout.png")));
    JLabel logoutBtn;

    public ProfilePage(String username) throws HeadlessException {
        this.username = username;

        setTitle("YouMe");

        setSize(1500, 800);
        getContentPane().setBackground(new Color(0xFDFDFE));
        setIconImage(logo.getImage());
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Statement ns = null;
        try {
            ns = MyConnection.connect().createStatement();
            ResultSet ex = null;
            ex = ns.executeQuery("select * from users where username = '" + username + "'");
            ex.next();

            name.setText("Name: " + ex.getString(2));
            surname.setText("Surname: " + ex.getString(3));
            if (ex.getString(6)==null)
                facebook.setText("Facebook: Not Provided");
            else
                facebook.setText("Facebook: " + ex.getString(6));
            if (ex.getString(7)==null)
                instagram.setText("Instagram: Not Provided");
            else
                instagram.setText("Instagram: " + ex.getString(7));
            if (ex.getString(8)==null)
                twitter.setText("Twitter: Not Provided ");
            else
                twitter.setText("Twitter: " + ex.getString(8));
            if (ex.getString(9)==null)
                whatsApp.setText("WhatsApp: Not Provided");
            else
                whatsApp.setText("WhatsApp: " + ex.getString(9));
            if (ex.getString(10)==null)
                viber.setText("Telegram: Not Provided");
            else
                viber.setText("Telegram: " + ex.getString(10));
            if (ex.getString(11)==null)
                telegram.setText("Viber: Not Provided");
            else
                telegram.setText("Viber: " + ex.getString(11));
            source = ex.getString(12);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        homeMenu.setFont(new Font("none", Font.PLAIN, 20));
        homeMenu.setForeground(new Color(0xC1C1C1 ));

        profileMenu.setFont(new Font("none", Font.PLAIN, 20));
        profileMenu.setForeground(new Color(0x5DC0F9));

        homeMenu.addMouseListener(this);
        profileMenu.addMouseListener(this);

        menu.setBorderPainted(false);
        menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        menu.add(Box.createHorizontalStrut(100));
        menu.add(profileMenu);
        menu.add(Box.createHorizontalStrut(70));
        menu.add(homeMenu);
        menu.setBackground(new Color(0xFDFDFE));

        setJMenuBar(menu);

        System.out.println(source);
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


        name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        name.setFont(new Font("none", Font.PLAIN, 20));
        name.setForeground(new Color(0xC1C1C1));
        surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        surname.setFont(new Font("none", Font.PLAIN, 20));
        surname.setForeground(new Color(0xC1C1C1));
        facebook.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        facebook.setFont(new Font("none", Font.PLAIN, 20));
        facebook.setForeground(new Color(0xC1C1C1));
        instagram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        instagram.setFont(new Font("none", Font.PLAIN, 20));
        instagram.setForeground(new Color(0xC1C1C1));
        twitter.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        twitter.setFont(new Font("none", Font.PLAIN, 20));
        twitter.setForeground(new Color(0xC1C1C1));
        whatsApp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        whatsApp.setFont(new Font("none", Font.PLAIN, 20));
        whatsApp.setForeground(new Color(0xC1C1C1));
        viber.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        viber.setFont(new Font("none", Font.PLAIN, 20));
        viber.setForeground(new Color(0xC1C1C1));
        telegram.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xC1C1C1)));
        telegram.setFont(new Font("none", Font.PLAIN, 20));
        telegram.setForeground(new Color(0xC1C1C1));

        JPanel InfoPanel = new JPanel();
        InfoPanel.setBounds(700, 0, 600, 720);
        InfoPanel.setLayout(new GridLayout(11, 1));
        InfoPanel.setOpaque(true);
        InfoPanel.setBackground(new Color(0xFDFDFE));


        JLabel user = new JLabel("<html>" +
                "<font size='72' color='#7D7D7D'><strong>" + username + "</strong></font>" +
                "</html>");
        Image btn = (edit).getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT);
        edit = new ImageIcon(btn);
        editBtn = new JLabel(edit);
        editBtn.addMouseListener(this);
        JPanel upper = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));

        upper.setBackground(new Color(0xFDFDFE));
        upper.add(user);
        upper.add(editBtn);
        InfoPanel.add(upper);
        InfoPanel.add(name);
        InfoPanel.add(surname);
        InfoPanel.add(facebook);
        InfoPanel.add(instagram);
        InfoPanel.add(twitter);
        InfoPanel.add(whatsApp);
        InfoPanel.add(viber);
        InfoPanel.add(telegram);

        Image btn1 = (logout).getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT);
        logout = new ImageIcon(btn1);
        logoutBtn = new JLabel(logout);
        logoutBtn.addMouseListener(this);
        JPanel below = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        below.setBackground(new Color(0xFDFDFE));
        below.add(logoutBtn);
        InfoPanel.add(below);

        add(InfoPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == homeMenu) {
            setVisible(false);
            new HomePage(username);
        }
        if(e.getSource()==logoutBtn){
            setVisible(false);
            new LogIn();
        }
        if (e.getSource()==editBtn){
            setVisible(false);
            new EditProfile(username);
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

        if (e.getSource() == homeMenu) {
            homeMenu.setForeground(new Color(0x5DC0F9));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == homeMenu) {
            homeMenu.setForeground(new Color(0xC1C1C1));
        }

    }
}