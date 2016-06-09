package server.dao;

import server.SQLDatabase;
import server.User;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class UserDAO {


    private static Connection connection = SQLDatabase.ConnectDatabase();

    //insertion dans la base de données d'un nouvel utilisateur
    public static User add_user(User myuser) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users (`username`, `mail`, `password`) VALUE (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, myuser.getUsername());
            pstmt.setString(2, myuser.getMail());
            pstmt.setString(3, myuser.getPassword());

            //execution du statement (requete)
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next())
                myuser.setUserId(rs.getLong(1));
            System.out.println("> Utilisateur : " + myuser.getUsername() + " ajouté à la base users avec l'id " + myuser.getUser_id());
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return myuser;
    }

    

    //Suppression d'un utilisateur avec en param USERNAME et MAIL
    public static void del_user(String username, String mail){

        try {

            String query = "DELETE from users WHERE username=? AND mail=?";
            PreparedStatement pstmnt = connection.prepareStatement(query);

            pstmnt.setString(1,username);
            pstmnt.setString(2,mail);
            pstmnt.executeUpdate();

            int rowsUpdated = pstmnt.executeUpdate();

            if (rowsUpdated == 0) {
                System.out.println("> L'utilisateur "+username+" avec l'adresse "+mail+" est introuvable");
            } else {
                System.out.println("> Utilisateur"+username+" a été supprimé de la base users");
            }

            pstmnt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //methode pour envoyer la date  sous forme  1994-02-25
    public static String DateToString(int year,int month, int day){

        NumberFormat yearformat = new DecimalFormat("0000");
        NumberFormat monthformat = new DecimalFormat("00");

        if(year>2016 || year <1000){
            System.out.println("L'année est incohérente");
        }

        if(month>13 || month <0){
            System.out.println("Le mois est incohérent");
        }

        if(day>32 || day <0){
            System.out.println("Le jour est incohérent");
        }

        String s = yearformat.format(year)+"-"+monthformat.format(month)+"-"+monthformat.format(day);

        return s;
    }


}
