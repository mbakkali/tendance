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

    public int user_id;
    public String username;
    public String mail;
    public String profilpicture;
    public String bio;
    public boolean sex;
    public boolean priv;
    public int phone;
    public String password; 


    public User(int user_id, 
                String username, 
                String mail, 
                String profilpicture, 
                String bio, 
                boolean sex, 
                boolean priv, 
                int phone, 
                String age, 
                String password) {

        this.user_id = user_id;
        this.username = username;
        this.mail = mail; 
        this.profilpicture = profilpicture; 
        this.bio = bio; 
        this.sex= sex; 
        this.priv=priv; 
        this.phone = phone; 
        this.password = password; 
    }


    //insertion dans la base de données d'un nouvel utilisateur 
    public static void add_user(Connection connection, User myuser)throws SQLException{
    try {
 

        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users (`username`, `mail`, `bio`, `sex`, `phone`, `private`, `age`) VALUE (?,?,?,?,?,?,?)");
        pstmt.setString(1,myuser.username); 
        pstmt.setString(2,myuser.mail); 
        pstmt.setString(3,myuser.bio); 
        pstmt.setBoolean(4,myuser.sex); 
        pstmt.setInt(5,myuser.phone); 
        pstmt.setBoolean(6,myuser.priv); 
        pstmt.setString(7,myuser.age); 

        //execution du statement (requete)
        pstmt.executeUpdate();

        System.out.println("> Utilisateur : "+myuser.username+" ajouté à la base users");
        pstmt.close(); 

    } catch (Exception e) {
      e.printStackTrace();
    }

  }




    //Suppression d'un utilisateur avec en param USERNAME et MAIL 
    public static void del_user(Connection connection, String username, String mail){

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


