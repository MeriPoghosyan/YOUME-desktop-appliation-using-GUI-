package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class MyConnection {

        public static Connection connect()
        {
            Connection con= null;
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/youme","root","M19052001");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return con;
        }
    }

