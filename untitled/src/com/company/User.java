package com.company;

public class User {
    private int user_id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String facebook;
    private String instagram;
    private String twitter;
    private String whatsapp;
    private String telegram;
    private String viber;
    private String image_name;

    public User(int user_id, String name, String surname, String username, String password, String facebook, String instagram, String twitter, String whatsapp, String telegram, String viber, Boolean status, String image_name) {
        this.user_id = user_id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.facebook = facebook;
        this.instagram = instagram;
        this.twitter = twitter;
        this.whatsapp = whatsapp;
        this.telegram = telegram;
        this.viber = viber;
        this.image_name = image_name;
    }

    public User() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getViber() {
        return viber;
    }

    public void setViber(String viber) {
        this.viber = viber;
    }


    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }
}

