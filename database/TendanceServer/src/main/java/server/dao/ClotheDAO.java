package server.dao;

import org.springframework.util.FileCopyUtils;
import server.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        return rowsUpdated != 0;
    }

    public static void update_clothe(Clothe clothe) throws SQLException{


        PreparedStatement ps = connection.prepareStatement(
                "  UPDATE `Tendance`.`clothes` " +
                        "SET `clothe_id` = ?," +
                        "`clothe_photo` = ?, " +
                        "`clothe_type` = ?, " +
                        "`user_id` = ?, " +
                        "`clothe_timestamp` = ?" +
                        "WHERE clothes.clothe_id = ?;");


        ps.setLong(1, clothe.getClothe_id());
        ps.setString(2, clothe.getClothe_photo());
        ps.setLong(3, clothe.getClothe_type());
        ps.setLong(4, clothe.getUser_id());
        ps.setString(5, clothe.getClothe_timestamp());
        ps.setLong(6, clothe.getClothe_id());

        ps.executeUpdate();

        ps.close();
        System.out.println("> Clothe : " + clothe.getClothe_id() + " du  " + clothe.getClothe_timestamp() + " a été mis à jour");



    }


    public Clothe addPhotoToClothe(ClotheWithFile clotheWithFile) throws SQLException {

        String name = UUID.randomUUID().toString();
        String path = Clothe.ROOT+"/"+name+".png";
        Clothe clothe = clotheWithFile;

        if (clotheWithFile.getFile().exists()) {
            try {

                //Saving the picture in the the ROOT directory
                FileCopyUtils.copy(clotheWithFile.getFile(), new File(path));

                //Updating database
                clothe.setClothe_photo(path);
                update_clothe(clothe);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Fichier non trouvé");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return clothe;
    }


    public Clothe getClotheById(long id) throws SQLException{
        Clothe clothe = null;

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM clothes WHERE clothe_id = ?");
        ps.setLong(1,id);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){
             clothe = new Clothe(
                    rs.getLong("clothe_id"),
                    rs.getLong("user_id"),
                    rs.getLong("clothe_type"),
                    rs.getString("clothe_photo"),
                    rs.getString("clothe_timestamp")
            );
        }
        return clothe;
    }

    public List<Clothe> getClothesOfOwner(long id) throws SQLException{
        List<Clothe> clothes = new ArrayList<>();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM clothes WHERE user_id = ?");
        ps.setLong(1,id);

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
