package server.dao;

import server.SQLDatabase;
import server.User;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class UserDAO {

    private static Connection connection = SQLDatabase.connectDatabase();
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

    public static User update_user(User myuser){
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE users " +
                    "SET users.username = ?, users.mail = ?," +
                    "users.bio = ?, users.male = ?, users.phone = ?, users.age = ?, users.profil_picture = ? " +
                    "WHERE users.user_id = ?");
            ps.setString(1, myuser.getUsername());
            ps.setString(2, myuser.getMail());
            ps.setString(3, myuser.getBio());
            ps.setBoolean(4, myuser.isMale());
            ps.setString(5, myuser.getPhone());
            ps.setString(6, myuser.getAge());
            ps.setString(7, myuser.getProfilpicture());

            ps.setLong(8,myuser.getUser_id());

        }catch (SQLException e){
            System.err.println("Erreur update_user");
        }
        return myuser;
    }

    public static User getUserByMailAndPassword(String mail, String password) throws SQLException {
        User user = null;
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE users.mail = ? AND users.password = ? ;");
        ps.setString(1,mail);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            user = new User(
                    rs.getLong("user_id"),
                    rs.getString("username"),
                    rs.getString("mail"),
                    rs.getString("profil_picture"),
                    rs.getString("bio"),
                    rs.getBoolean("male"),
                    rs.getBoolean("private"),
                    rs.getString("phone"),
                    rs.getString("age")
            );
        }
        return user;
    }
    public static User getUserByID(long id) throws SQLException {
        User user = null;
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE users.mail = ? AND users.password = ? ;");
        ps.setLong(1,id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            user = new User(
                    rs.getLong("user_id"),
                    rs.getString("username"),
                    rs.getString("mail"),
                    rs.getString("profil_picture"),
                    rs.getString("bio"),
                    rs.getBoolean("male"),
                    rs.getBoolean("private"),
                    rs.getString("phone"),
                    rs.getString("age")
            );
        }
        return user;
    }
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
