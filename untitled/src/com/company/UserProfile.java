package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class UserProfile extends JFrame implements ActionListener, MouseListener, FocusListener, KeyListener {
private String user;
    private String username;
    ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/logo.png")));
    ImageIcon back = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/back.png")));

    JLabel name = new JLabel();
    JLabel surname = new JLabel();
    JLabel facebook = new JLabel();
    JLabel instagram = new JLabel();
    JLabel twitter = new JLabel();
    JLabel whatsApp = new JLabel();
    JLabel viber = new JLabel();
    JLabel telegram = new JLabel();
    String source;
    JLabel backBtn;

    public UserProfile(String username, String userProfile) throws HeadlessException {
        this.username = username;
        this.user = userProfile;

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
            ex = ns.executeQuery("select * from users where username = '" + userProfile + "'");
            ex.next();

            name.setText("Name: " + ex.getString(2));
            surname.setText("Surname: " + ex.getString(3));
            if (ex.getString(6) == null)
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
            if (ex.getString(11)== null)
                telegram.setText("Viber: Not Provided");
            else
                telegram.setText("Viber: " + ex.getString(11));
            source = ex.getString(12);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Image btn = (back).getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT);
        back = new ImageIcon(btn);
        backBtn = new JLabel(back);
        backBtn.addMouseListener(this);
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
        InfoPanel.setLayout(new GridLayout(10, 1));
        InfoPanel.setOpaque(true);
        InfoPanel.setBackground(new Color(0xFDFDFE));


        JLabel user = new JLabel("<html>" +
                "<font size='72' color='#7D7D7D'><strong>" + userProfile + "</strong></font>" +
                "</html>");
        JPanel upper = new JPanel(new FlowLayout(FlowLayout.LEFT, 70, 0));
        upper.setBackground(new Color(0xFDFDFE));
        upper.add(backBtn);
        upper.add(user);
        InfoPanel.add(upper);
        InfoPanel.add(name);
        InfoPanel.add(surname);
        InfoPanel.add(facebook);
        InfoPanel.add(instagram);
        InfoPanel.add(twitter);
        InfoPanel.add(whatsApp);
        InfoPanel.add(viber);
        InfoPanel.add(telegram);

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