package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import static javax.swing.BorderFactory.createEmptyBorder;

public class HomePage extends JFrame implements ActionListener, MouseListener, FocusListener, KeyListener {
    private String username;
    ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/logo.png")));
    ImageIcon friends = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/home.jpg")));
    Image img1 = (friends).getImage().getScaledInstance(friends.getIconWidth() / 5, friends.getIconHeight() / 5, Image.SCALE_SMOOTH);
    ImageIcon searchIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("photos/search.png")));
    Image search = (searchIcon).getImage().getScaledInstance(searchIcon.getIconWidth() / 30, searchIcon.getIconHeight() / 30, Image.SCALE_SMOOTH);

    JMenuBar menu = new JMenuBar();
    JMenu homeMenu = new JMenu("Home");
    JMenu profileMenu = new JMenu("Profile");
    JPanel usersPanel = new JPanel();
    JTextField searchBar = new JTextField();

    public HomePage(String username) throws HeadlessException {
        this.username = username;

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
        leftPanel.setBorder(new EmptyBorder(10, 50, 10, 50));

        leftPanel.setBounds(0, 0, 1024, 800);
        leftPanel.add(forPhoto);
        leftPanel.add(bluePanel);
        add(leftPanel);

        homeMenu.setFont(new Font("none", Font.PLAIN, 20));
        homeMenu.setForeground(new Color(0x5DC0F9));

        profileMenu.setFont(new Font("none", Font.PLAIN, 20));
        profileMenu.setForeground(new Color(0xC1C1C1));

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

        Statement forCount;
        int numOfUsers = 0;
        try {
            forCount = MyConnection.connect().createStatement();
            ResultSet countUsers = forCount.executeQuery("select Count(*) from users");

            while (countUsers.next()) {
                numOfUsers = countUsers.getInt("count(*)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(numOfUsers);
        usersPanel.setLayout(new GridLayout(numOfUsers, 1, 0, 30));
        usersPanel.setOpaque(true);
        usersPanel.setBackground(new Color(0xFDFDFE));
        //usersPanel.setBounds(800,200,450,500);
        usersPanel.setPreferredSize(new Dimension(450, numOfUsers*125));


        JLabel signUpText = new JLabel("<html>" +
                "<font size='72' color='#7D7D7D'><strong>People</strong></font>" +
                "</html>");
        JLabel descText = new JLabel("<html>" +
                "<font size='3' color='#C1C1C1'>Make new connections by scrolling down and exploring profiles</font>" + "</html>");
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));


        searchBar.setPreferredSize(new Dimension(300, 30));
        searchBar.setFont(new Font("none", Font.PLAIN, 10));
        searchBar.setForeground(new Color(0x5DC0F9));
        searchBar.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x5DC0F9)));
        searchBar.addKeyListener(this);

       searchIcon = new ImageIcon(search);
        JLabel searchUser = new JLabel(searchIcon);

        rightPanel.setOpaque(true);
        rightPanel.setBackground(new Color(0xFDFDFE));
        rightPanel.setBounds(800, 10, 500, 720);

        rightPanel.add(signUpText);
        rightPanel.add(descText);
        rightPanel.add(searchBar);
        rightPanel.add(searchUser);

        showUsers();



        JScrollPane scrollPane = new JScrollPane(usersPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(470,500));
       // scrollPane.setPreferredSize(new Dimension(460, 500));


        searchBar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                usersPanel.removeAll();
                usersPanel.revalidate();
                usersPanel.repaint();
                showUsers();
            }
        });
        rightPanel.add(scrollPane);
        add(rightPanel);
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
        if(e.getSource()==searchBar){}

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == profileMenu) {
            setVisible(false);
            new ProfilePage(username);
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
        if (e.getSource() == profileMenu) {
            profileMenu.setForeground(new Color(0x5DC0F9));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == profileMenu) {
            profileMenu.setForeground(new Color(0xC1C1C1));
        }
    }

    public void showUsers() {
        UserDAO dao = new UserDAO();
        dao.fillUsers();
        String[][] data = dao.gettingDataForHome();
            for (String[] datum : data) {
                if((datum[1].contains(searchBar.getText()) || datum[2].contains(searchBar.getText()) || datum[3].contains(searchBar.getText()) || searchBar.getText().equals("")) && !(datum[3].equals(username))) {
                    String pic = datum[11];
                    ImageIcon profilePic = new ImageIcon(pic);
                    profilePic.setImage(profilePic.getImage().getScaledInstance(95, 90, Image.SCALE_DEFAULT));
                    JLabel forProfPic = new JLabel(profilePic);
                    String userName= datum[3];
                    JLabel name = new JLabel(datum[1] + " " + datum[2]);
                    System.out.println("\n" + name);
                    name.setFont(new Font("none", Font.PLAIN, 20));
                    name.setForeground(new Color(0x7D7D7D));
                    JPanel person = new JPanel(new GridLayout(1, 3));
                    person.setPreferredSize(new Dimension(450, 90));
                    person.add(forProfPic);
                    person.setBackground(new Color(0xFDFDFE));
                    person.add(name);
                    person.addMouseListener(new MouseListener() {

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            setVisible(false);
                            new UserProfile(username,userName);
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            person.setBackground(new Color(0xD6EDFF));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            person.setBackground(new Color(0xFDFDFE));
                        }
                    });

                    usersPanel.add(person);
                }
            }
        }
    }
