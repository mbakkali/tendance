package server.dao;

import server.SQLDatabase;
import server.User;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {

    //Connexion à la base de données. Le "connection" est le descripteur (statique sur cette classe)
    private static Connection connection = SQLDatabase.connectDatabase();



    public static User add_user(User myuser) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users (`username`, `mail`, `password`) VALUE (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, myuser.getUsername());
            pstmt.setString(2, myuser.getMail());
            pstmt.setString(3, myuser.getPassword());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next())
                myuser.setUserId(rs.getLong(1));
            System.out.println("> Utilisateur : " + myuser.getUsername() + " ajouté à la base users avec l'id " + myuser.getUser_id());
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Erreur Add_user");        }
        return myuser;
    }



    public static void del_user(User user){

        try {

            String query = "DELETE from users WHERE username=? AND user_id=?";
            PreparedStatement pstmnt = connection.prepareStatement(query);

            pstmnt.setLong(1,user.getUser_id());
            pstmnt.setString(2,user.getUsername());
            pstmnt.executeUpdate();

            int rowsUpdated = pstmnt.executeUpdate();

            if (rowsUpdated == 0) {
                System.out.println("> L'utilisateur "+user.getUsername()+" avec l'ID "+user.getUser_id()+" est introuvable");
            } else {
                System.out.println("> Utilisateur"+user.getUsername()+" a été supprimé de la base users");
            }
            pstmnt.close();
        } catch (SQLException e) {
            System.out.println("Erreur Del_user");
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



    public static User getUserByMailAndPassword(String mail, String password) throws  SQLException {
         User user = null;

             PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE users.mail = ? AND users.password = ? ;");
             ps.setString(1, mail);
             ps.setString(2, password);
             ResultSet rs = ps.executeQuery();

             if (rs.next()) {
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


    public static User getUserByID(long id) {
        User user = null;
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE users.user_id = ? ;");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
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

        }
        catch (SQLException e){
            System.out.println("Erreur GetByUserID");
        }
        return user;
    }


    public static List<User> getFriends(long id) {
        User user = null;
        List<User> friends = new ArrayList<>();
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT relationships.friend_id FROM users, relationships WHERE users.user_id = relationships.user_id AND users.user_id = ? ;");
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                long friend_id = rs.getLong("friend_id");
                user = getUserByID(friend_id);
                friends.add(user);
            }

            rs.close();

        }
        catch (SQLException e){
            System.out.println("Erreur get Friends ");
        }
        return friends;
    }


    public static User getUserByUsername(String username )  {

        User user = null;
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE users.mail = ? AND users.password = ? ;");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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

        }
        catch (SQLException e){
        }
        return user;
    }

}
