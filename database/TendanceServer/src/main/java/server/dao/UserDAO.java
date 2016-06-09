package server.dao;

import server.SQLDatabase;
import server.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class UserDAO {


    private static Connection connection = SQLDatabase.ConnectDatabase();

    //insertion dans la base de données d'un nouvel utilisateur
    public static void add_user(User myuser) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users (`username`, `mail`, `bio`, `male`, `phone`, `private`, `age`, `password`) VALUE (?,?,?,?,?,?,?,?)");
            
            pstmt.setString(1, myuser.getUsername());
            pstmt.setString(2, myuser.getMail());
            pstmt.setString(3, myuser.getBio());
            pstmt.setBoolean(4, myuser.isMale());
            pstmt.setString(5, myuser.getPhone());
            pstmt.setBoolean(6, myuser.isPriv());
            pstmt.setString(7, myuser.getAge());
            pstmt.setString(8, myuser.getPassword());

            //execution du statement (requete)
            pstmt.executeUpdate();

            System.out.println("> Utilisateur : " + myuser.getUsername() + " ajouté à la base users");
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
