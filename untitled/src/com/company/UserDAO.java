package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAO {
    private List<User> userInfo = new ArrayList<>();
    private Statement statement = null;
    private ResultSet resultSet;
    public String toSqlString (String s){
        if (s == null) return null;
        return '"' + s + '"';
    }
    public String spaceFree(String s){
        if(s != null)
        s = s.replaceAll("\\s", "");
        return s;
    }
    public void insertIntoDb(User user){
        try {
            statement = MyConnection.connect().createStatement();
            String db = "insert into users values (NULL," + toSqlString(user.getName()) + ',' + toSqlString(user.getSurname()) +
                    ',' + toSqlString(user.getUsername()) + ',' + toSqlString(user.getPassword()) + ',' + toSqlString(user.getFacebook())
                    + ',' + toSqlString(user.getInstagram()) + ',' + toSqlString(user.getTwitter()) + ',' + spaceFree(toSqlString(user.getWhatsapp())) + ','
                    + spaceFree(toSqlString(user.getTelegram())) + ',' + spaceFree(toSqlString(user.getViber())) + ','  + (toSqlString(user.getImage_name())  + ")");
            System.out.println();
            statement.executeUpdate(db);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void fillUsers() {
        try {
            statement = MyConnection.connect().createStatement();
            resultSet = statement.executeQuery("Select * from users");
            while (resultSet.next()) {
                User user = new User();

                user.setUser_id(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setUsername(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setFacebook(resultSet.getString(6));
                user.setInstagram(resultSet.getString(7));
                user.setTwitter(resultSet.getString(8));
                user.setWhatsapp(resultSet.getString(9));
                user.setTelegram(resultSet.getString(10));
                user.setViber(resultSet.getString(11));
                user.setImage_name(resultSet.getString(12));


                userInfo.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public String[][] gettingDataForHome() {
            String[][] data = new String[userInfo.size()][12];
            for (int i = 0; i < userInfo.size(); i++) {

                data[i][0] = String.valueOf(userInfo.get(i).getUser_id());
                data[i][1] = userInfo.get(i).getName();
                data[i][2] = userInfo.get(i).getSurname();
                data[i][3] = userInfo.get(i).getUsername();
                data[i][4] = userInfo.get(i).getPassword();
                data[i][5] = userInfo.get(i).getFacebook();
                data[i][6] = userInfo.get(i).getInstagram();
                data[i][7] = userInfo.get(i).getTwitter();
                data[i][8] = userInfo.get(i).getWhatsapp();
                data[i][9] = userInfo.get(i).getTelegram();
                data[i][10] = userInfo.get(i).getViber();
                data[i][11] = userInfo.get(i).getImage_name();
            }
            return data;
        }
    public void ChangeInDB(User user){
        try {
            statement = MyConnection.connect().createStatement();
            String db = "UPDATE users SET username = " + toSqlString(user.getUsername()) +
                    ", name ="+ toSqlString(user.getName()) +
                    ", surname = "+ toSqlString(user.getSurname()) +
                    ", password = " + toSqlString(user.getPassword()) +
                    ", facebook = " + toSqlString(user.getFacebook()) +
                     ", instagram = " + toSqlString(user.getInstagram()) +
                    ",  twitter = " + toSqlString(user.getTwitter()) +
                    ",  whatsapp = " + spaceFree(toSqlString(user.getWhatsapp())) +
                    ",  telegram = " + spaceFree(toSqlString(user.getTelegram())) +
                    ",  viber = " + spaceFree(toSqlString(user.getViber())) +
                    ",  image_name = " + toSqlString(user.getImage_name()) +
                     " WHERE user_id =  " + user.getUser_id();
           statement.executeUpdate(db);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
