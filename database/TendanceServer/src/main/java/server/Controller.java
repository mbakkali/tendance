package server;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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





@RestController
public class Controller {

    private final int counter = 0; 


    @RequestMapping("/user")
    public User myuser(@RequestParam(value="username", defaultValue="myusername") String username) {

    	

    	/*counter.incrementAndGet(),"mehdi","test@mail.com","/img/mehdi.jpeg","bio",true,true,,"passwd" */

        return null; 
       
    }



    /*
public static ArrayList<String> getFromTable(Connection connection, String table, String row, String search){
    try {

      String query = "SELECT * FROM "+table+" where "+row+" = '"+search+"';";
      //System.out.println("On utilise la requete suivante : "+ query);

      Statement state = connection.createStatement();
      ResultSet result = state.executeQuery(query);
      ResultSetMetaData resultMeta = result.getMetaData();

      ArrayList<String> resultquery = new ArrayList<String>(); 

      while(result.next()){         
        for(int i = 1; i <= resultMeta.getColumnCount(); i++){
          resultquery.add(result.getObject(i).toString() );  
          System.out.print(" ");
        }
      }
      
      result.close(); 
      state.close(); 

      return resultquery; 


      } catch (Exception e) {
        e.printStackTrace();
      }

      return null;

  }
*/

/*
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
*/

/*
public static void DisplayTable(Connection connection, String table) {
    try {

      Statement state = connection.createStatement();
      ResultSet result = state.executeQuery("SELECT * FROM "+table);
      ResultSetMetaData resultMeta = result.getMetaData();
      System.out.println("\n**********************************");

      //On affiche le nom des colonnes
      for(int i = 1; i <= resultMeta.getColumnCount(); i++)
      System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
      System.out.println("\n**********************************");
         

      while(result.next()){         
        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
          System.out.print("\t" + result.getObject(i).toString() + "\t |");
          System.out.println("\n---------------------------------");
      }

      result.close();
      state.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

*/

/*
Male = true (1);  
Privateaccount = true (1)
pour le user_id on est en auto_incrément => on insert null
Attention on insert
*/

/*
 public static void add_user(Connection connection, 
                              String username, 
                              String mail, 
                              String bio, 
                              Boolean sex, 
                              int phone, 
                              Boolean priv,
                              int birth_year,
                              int birth_month,
                              int birth_day
                              String password)throws SQLException{
    try {

        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users (`username`, `mail`, `bio`, `sex`, `phone`, `private`, `age`,`password`) VALUE (?,?,?,?,?,?,?,?)");
        pstmt.setString(1,username); 
        pstmt.setString(2,mail); 
        pstmt.setString(3,bio); 
        pstmt.setBoolean(4,sex); 
        pstmt.setInt(5,phone); 
        pstmt.setBoolean(6,priv); 
        pstmt.setString(7,DateToString(birth_year,birth_month,birth_day)); 
        pstmt.setString(8,password);

        pstmt.executeUpdate();

        System.out.println("> Utilisateur : "+username+" ajouté à la base users");
        pstmt.close(); 

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
*/

/*
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
  */



//rempli la db avec des user1 --> usernum

  /*
  public static void fulldatabase(Connection connection,int user_num)throws SQLException{

    for(int i=1; i<user_num; i++){
        String username = "usertest"+i;
        String usermail = "mailuser"+i+"@gmail";
        String userbio = "mabio"+i;
        String userpassword = "mypassword"+i; 
     
        add_user(connection,username,usermail,userbio,true,661345282+i,true,1994,2,25,userpassword); 

      }

  }
*/

/*
public static Connection ConnectDatabase() throws SQLException{

    System.out.println("> Début de la connection à la base de données");

    String url = "jdbc:mysql://90.66.114.198:3306/Tendance";
    String user = "monitor";
    String passwd = "tendance2016";

    try {

      Class.forName("com.mysql.jdbc.Driver");

    } catch (ClassNotFoundException e) {

      System.out.println("> Problème de JDBC Driver?");
      e.printStackTrace();

    }

    System.out.println("> Le driver JDBC est bon!");

     Connection connection = null;

    try {

      connection = DriverManager.getConnection(url,user,passwd);

    } catch (SQLException e) {

      System.out.println("> Problème de connexion à la base de données, différents sources possibles (pas les droits, dbb inexistante, ...)");
      e.printStackTrace();

    }

    if (connection != null) {
      System.out.println("> Vous avez le controle de la base de données Tendance");
    } else {
      System.out.println("> Problème de connexion à la base de données, différents sources possibles (pas les droits, dbb inexistante, ...)");
    }
    return connection; 
  }


*/
}