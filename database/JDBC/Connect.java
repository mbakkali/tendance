import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData; 


public class Connect {


//Affiche la table
 public static void DisplayTable(Connection conn, String table) {
    try {


    //Création d'un objet Statement
      Statement state = conn.createStatement();
      //L'objet ResultSet contient le résultat de la requête SQL
      ResultSet result = state.executeQuery("SELECT * FROM "+table);
      //On récupère les MetaData
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

/*
Male = true (1);  
Privateaccount = true (1)
pour le user_id on est en auto_incrément => on insert null
*/
 public static void add_user(Connection conn, 
                              String username, 
                              String mail, 
                              String bio, 
                              Boolean sex, 
                              int phone, 
                              Boolean priv,
                              int age) throws SQLException{
    try {

        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (`username`, `mail`, `bio`, `sex`, `phone`, `private`, `age`) VALUE (?,?,?,?,?,?,?)");
        pstmt.setString(1,username); 
        pstmt.setString(2,mail); 
        pstmt.setString(3,bio); 
        pstmt.setBoolean(4,sex); 
        pstmt.setInt(5,phone); 
        pstmt.setBoolean(6,priv); 
        pstmt.setInt(7,age); 

        pstmt.executeUpdate();

        System.out.println("utilisateur"+username+" ajouté à la base users");
        pstmt.close(); 

    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  public static void del_user(Connection conn, String username, String mail){
    try {

        //Création d'un objet Statement
        Statement state = conn.createStatement();
        //L'objet ResultSet contient le résultat de la requête SQL
        state.executeUpdate("DELETE from users WHERE username="+username+" AND mail="+mail+"");

          System.out.println("utilisateur"+username+" a été supprimé de la base users");
          state.close(); 

      } catch (Exception e) {
        e.printStackTrace();
      }


  }

//rempli la db avec des user1 --> usernum
  public static void fulldatabase(Connection conn,int user_num)throws SQLException{

    for(int i=1; i<user_num; i++){
        String username = "usertest"+i;
        String usermail = "mailuser"+i+"@gmail";
        String userbio = "mabio"+i;
     
        add_user(conn,username,usermail,userbio,true,661345282+i,true,2*i); 

      }

  }




  public static void main(String[] args) {      

    try {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver O.K.");

      String url = "jdbc:mysql://localhost:3306/Tendance";
      String user = "root";
      String passwd = "bouskoura2K15";

      Connection conn = DriverManager.getConnection(url, user, passwd);
      System.out.println("Connexion effective !");


      
      del_user(conn,"jib", "jib@gmail.com");
      DisplayTable(conn,"users"); 

         
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


 

}