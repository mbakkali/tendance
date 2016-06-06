package server;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;
import java.text.*; 

public class User {

    private int user_id;
    private String username;
    private String mail;
    private String profilpicture;
    private String bio;
    private boolean male;
    private boolean priv;
    private int phone;
    private String password;
    private String age;


    public User(int user_id, 
                String username, 
                String mail, 
                String profilpicture, 
                String bio, 
                boolean male,
                boolean priv, 
                int phone, 
                String age, 
                String password) {

        this.user_id = user_id;
        this.username = username;
        this.mail = mail; 
        this.profilpicture = profilpicture; 
        this.bio = bio; 
        this.male= male;
        this.priv=priv; 
        this.phone = phone; 
        this.password = password; 
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getProfilpicture() {
        return profilpicture;
    }

    public String getBio() {
        return bio;
    }

    public boolean isMale() {
        return male;
    }

    public boolean isPriv() {
        return priv;
    }

    public int getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getAge() {
        return age;
    }

  }












