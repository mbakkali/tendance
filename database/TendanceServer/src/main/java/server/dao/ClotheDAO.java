package server.dao;

import server.Clothe;
import server.SQLDatabase;
import server.Type;
import server.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrik on 10/06/16.
 */
public class ClotheDAO {

    public static Connection connection = SQLDatabase.connectDatabase();

    public List<Type> getAllTypes() throws SQLException {
        List<Type> types = new ArrayList<>();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM types;");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
         types.add(new Type(rs.getLong("type_id"),
                            rs.getString("type_name")));
        }
        return types;
    }
    public Clothe add_clothe(Clothe clothe) throws SQLException{

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO `Tendance`.`clothes` (`clothe_photo`, `clothe_type`, `user_id`,`clothe_timestamp`) VALUES (?, ?, ?, ?);");

            pstmt.setString(1, clothe.getClothe_photo());
            pstmt.setLong(2, clothe.getClothe_type());
            pstmt.setLong(3, clothe.getUser_id());
            pstmt.setString(4, SQLDatabase.CurrentTimestampToString());

            pstmt.executeUpdate();

            System.out.println("> Clothe : " + clothe.getClothe_photo() + " ajouté à la table Clothe à "+ clothe.getClothe_timestamp());
            pstmt.close();


        return clothe;

    }


    public boolean del_clothe(long id) throws SQLException {
        String query = "DELETE from clothes WHERE clothe_id=?;";
        PreparedStatement pstmnt = connection.prepareStatement(query);

        pstmnt.setLong(1,id);
        pstmnt.executeUpdate();

        int rowsUpdated = pstmnt.executeUpdate();


        pstmnt.close();
        if (rowsUpdated == 0) {
            return false;
        } else {
            return true;
        }
    }

    public List<Clothe> getClothesOfOwner(User user) throws SQLException{
        List<Clothe> clothes = new ArrayList<>();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM clothes WHERE user_id = ?");
        ps.setLong(1,user.getUser_id());

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Clothe clothe = new Clothe(
                    rs.getLong("clothe_id"),
                    rs.getLong("user_id"),
                    rs.getLong("clothe_type"),
                    rs.getString("clothe_photo"),
                    rs.getString("clothe_timestamp")
            );
            clothes.add(clothe);
        }
        return clothes;
    }
    public List<Clothe> getClothesOfOwnerForType(User user, Type type) throws SQLException{
        List<Clothe> clothes = new ArrayList<>();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM clothes WHERE user_id = ? AND clothe_type = ?;");
        ps.setLong(1,user.getUser_id());
        ps.setLong(2, type.getType_id());
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Clothe clothe = new Clothe(
                    rs.getLong("clothe_id"),
                    rs.getLong("user_id"),
                    rs.getLong("clothe_type"),
                    rs.getString("clothe_photo"),
                    rs.getString("clothe_timestamp")
            );
            clothes.add(clothe);
        }
        return clothes;
    }




}
