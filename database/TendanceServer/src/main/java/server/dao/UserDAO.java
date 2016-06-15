package server.dao;

import server.SQLDatabase;
import server.User;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {

    private static Connection connection = SQLDatabase.connectDatabase();


    public User add_user(User myuser) throws SQLException {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users (`username`, `mail`, `password`) VALUE (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, myuser.getUsername());
            pstmt.setString(2, myuser.getMail());
            pstmt.setString(3, myuser.getPassword());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next())
                myuser.setUserId(rs.getLong(1));
            System.out.println("> Utilisateur : " + myuser.getUsername() + " ajouté à la base users avec l'id " + myuser.getUser_id());
            pstmt.close();
        return myuser;
    }

    public void del_user(User user) throws SQLException {

            String query = "DELETE from users WHERE user_id=?;";
            PreparedStatement pstmnt = connection.prepareStatement(query);

            pstmnt.setLong(1,user.getUser_id());
            pstmnt.executeUpdate();

            System.out.println("> Utilisateur"+user.getUsername()+" a été supprimé de la base users");
            pstmnt.close();
    }

    public User update_user(User myuser) throws SQLException {
            PreparedStatement ps = connection.prepareStatement("UPDATE users " +
                    "SET users.username = ?, users.mail = ?," +
                    "users.bio = ?, users.male = ?, users.phone = ?, users.age = ?, users.profil_picture = ? " +
                    "WHERE users.user_id = ?;");
            ps.setString(1, myuser.getUsername());
            ps.setString(2, myuser.getMail());
            ps.setString(3, myuser.getBio());
            ps.setBoolean(4, myuser.isMale());
            ps.setString(5, myuser.getPhone());
            ps.setString(6, myuser.getAge());
            ps.setString(7, myuser.getProfilpicture());

            ps.setLong(8,myuser.getUser_id());
            ps.executeUpdate();

        return myuser;
    }

    public User getUserByMailAndPassword(String mail, String password) throws SQLException {
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

    public User getUserByID(long id) throws SQLException {
        User user = null;
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
        return user;
    }

    public List<User> getFriends(Long user_id) throws SQLException {
        User target = null;
        List<User> friends = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("SELECT relationships.friend_id FROM users, relationships WHERE users.user_id = relationships.user_id AND users.user_id = ? ;");
            ps.setLong(1, user_id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                long friend_id = rs.getLong("friend_id");
                target = getUserByID(friend_id);
                friends.add(target);
            }

            rs.close();
        return friends;
    }

    public List<User> getUsersByUsername(String username) throws SQLException{
        List<User> users = new ArrayList<>();
        User user= null;
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE username LIKE ?");
        ps.setString(1,username);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            user = new User(rs.getLong("user_id"),
                            rs.getString("username"),
                            rs.getString("profil_picture"));
            users.add(user);
        }

        return users;
    }

    public void addFriend(User user,User friend) throws SQLException {

        if(isFriended(user,friend)==false){

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO `Tendance`.`relationships` (`friended`, `user_id`, `friend_id`) VALUES (CURRENT_TIMESTAMP, ?, ?)");
            pstmt.setLong(1, user.getUser_id());
            pstmt.setLong(2, friend.getUser_id());

            pstmt.executeUpdate();
            System.out.println(user.getUsername() +"et"+friend.getUser_id() +" sonts amis");
        }

        else{
            System.out.println("Deja amis");
        }

    }

    public boolean isFriended(User userA,User userB) throws SQLException {
        boolean bool = false;
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM relationships WHERE user_id =? AND friend_id =?;");

        ps.setLong(1,userA.getUser_id());
        ps.setLong(2,userB.getUser_id());

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            bool = true;
        }

        return bool;
    }


    public void delFriend(User user, User friend) throws SQLException {
        String query = "DELETE FROM `Tendance`.`relationships` WHERE `relationships`.`user_id` = ? AND `relationships`.`friend_id` = ?;";
        PreparedStatement pstmnt = connection.prepareStatement(query);

        pstmnt.setLong(1,user.getUser_id());
        pstmnt.setLong(2,friend.getUser_id());
        pstmnt.executeUpdate();


        int rowsUpdated = pstmnt.executeUpdate();

        System.out.println("L'amitié supprimée entre "+user.getUsername()+" et "+friend.getUsername());

        pstmnt.close();
    }
}
