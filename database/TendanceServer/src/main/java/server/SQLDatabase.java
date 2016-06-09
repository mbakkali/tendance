package server;

import java.sql.*;

/**
 * Created by Patrik on 06/06/2016.
 */
public class SQLDatabase {
    public static Connection ConnectDatabase(){

        System.out.println("> Début de la connection à la base de données");
        String url2 = "jdbc:mysql://192.168.1.13:3306/Tendance";
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


    //Affiche la table
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

}
