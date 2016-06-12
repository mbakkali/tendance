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
    public Clothe add_clothe(Clothe clothe) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO clothes (`owner`,`clothe_photo`) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setLong(1, clothe.getOwner());
        ps.setString(2, clothe.getClothe_photo());

        //TODO Voir si c'est ok...
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            clothe.setClothe_id(rs.getLong("clothe_id"));
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

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM clothes WHERE owner = ?");
        ps.setLong(1,user.getUser_id());

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Clothe clothe = new Clothe(
                    rs.getLong("clothe_id"),
                    rs.getLong("owner"),
                    rs.getLong("type"),
                    rs.getString("clothe_photo")
            );
            clothes.add(clothe);
        }
        return clothes;
    }
    public List<Clothe> getClothesOfOwnerForType(User user, Type type) throws SQLException{
        List<Clothe> clothes = new ArrayList<>();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM clothes WHERE owner = ? AND clothe_type = ?;");
        ps.setLong(1,user.getUser_id());
        ps.setLong(2, type.getType_id());
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Clothe clothe = new Clothe(
                    rs.getLong("clothe_id"),
                    rs.getLong("owner"),
                    rs.getLong("type"),
                    rs.getString("clothe_photo")
            );
            clothes.add(clothe);
        }
        return clothes;
    }



}
