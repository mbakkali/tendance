package server.dao;

import server.SQLDatabase;
import server.User;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


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

    public static boolean del_user(User user, String password) throws SQLException {

            String query = "DELETE from users WHERE user_id=? AND password=?";
            PreparedStatement pstmnt = connection.prepareStatement(query);

            pstmnt.setLong(1,user.getUser_id());
            pstmnt.setString(2,password);
            pstmnt.executeUpdate();

            int rowsUpdated = pstmnt.executeUpdate();


            pstmnt.close();
        if (rowsUpdated == 0) {
            System.out.println("> L'utilisateur "+user.getUsername()+" avec l'adresse "+user.getMail()+" est introuvable");
            return false;
        } else {
            System.out.println("> Utilisateur"+user.getUsername()+" a été supprimé de la base users");
            return true;
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

    public static List<User> getFriendsOfUser(User user){
        List<User> friends = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Tendance.users.* FROM Tendance.users " +
                                                                "INNER JOIN Tendance.relationships " +
                                                                "ON users.user_id = relationships.friend_id " +
                                                                "WHERE relationships.user_id = ?" +
                                                                "AND status = 2;");
            //TODO FIX DATABASE TO HAVE STATUS
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                friends.add(new User(
                        rs.getLong("user_id"),
                        rs.getString("username"),
                        rs.getString("mail"),
                        rs.getString("profil_picture"),
                        rs.getString("bio"),
                        rs.getBoolean("male"),
                        rs.getBoolean("priv"),
                        rs.getString("phone"),
                        rs.getString("age")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    public static List<User> getFriendRequestOfUser(User user){
        List<User> friend_requests= new ArrayList<>();

        return friend_requests;
    }

    public static boolean addFriend(long source, long target) {
        Calendar now = Calendar.getInstance();

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO relationships VALUES (user_id = ?, friend_id = ?, friended = NOW());");
            ps.setLong(1,source);
            ps.setLong(2,target);
            ps.executeQuery();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    public static boolean delFriend(long source, long target) {
        Calendar now = Calendar.getInstance();

        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM relationships WHERE user_id = ? AND friend_id = ?;");
            ps.setLong(1,source);
            ps.setLong(2,target);
            ps.executeQuery();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }


}
