package server.dao;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import server.Clothe;
import server.Outfit;
import server.SQLDatabase;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class OutfitDAO {
    private static Connection connection = SQLDatabase.connectDatabase();

    public static void add_outfit(Outfit outfit) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO outfits (`timestamp`, `description`, `photo`, `style_id`) VALUE (?,?,?,?)");

            pstmt.setString(1, SQLDatabase.CurrentTimestampToString());
            pstmt.setString(2, outfit.getDescription());
            pstmt.setString(3, outfit.getPhoto());
            pstmt.setLong(4, outfit.getStyle_id());


            pstmt.executeUpdate();

            System.out.println("> Outfit : " + outfit.getDescription() + " ajouté à la table Outfit à "+ outfit.getTimestamp());
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void del_outfit(Outfit outfit){

        try {

            String query = "DELETE from outfits WHERE outfit_id=?";
            PreparedStatement pstmnt = connection.prepareStatement(query);

            pstmnt.setLong(1,outfit.getOutfit_id());
            pstmnt.executeUpdate();

            System.out.println("> L'outfit"+outfit.getDescription()+" a été supprimé de la base users");

            pstmnt.close();
        } catch (SQLException e) {
            System.out.println("Erreur Del_outfit");
        }
    }

    public static Outfit getOutfitByID(long id) {
        Outfit outfit = null;
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM outfits WHERE outfits.outfit_id = ? ;");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                outfit = new Outfit(
                        rs.getLong("outfit_id"),
                        rs.getString("timestamp"),
                        rs.getString("description"),
                        rs.getString("photo"),
                        rs.getLong("style_id"),
                        rs.getLong("user_id"),
                        rs.getLong("event_id")
                );
            }
        }
        catch (SQLException e){
            System.out.println("Erreur GetOutfitByID");
        }
        return outfit;
    }



    public static void update_outfit(Outfit outfit) throws SQLException{
            PreparedStatement ps = connection.prepareStatement(
                  "  UPDATE `Tendance`.`outfits` SET `outfit_id` = ?,`timestamp` = ?, `description` = ?, `photo` = ?, `style_id` = ?, `user_id` = ?, `event_id` = ? WHERE `outfits`.`outfit_id` = ?;");


            ps.setLong(1, outfit.getOutfit_id());
            ps.setString(2, outfit.getTimestamp());
            ps.setString(3, outfit.getDescription());
            ps.setString(4, outfit.getPhoto());
            ps.setLong(5, outfit.getStyle_id());
            ps.setLong(6, outfit.getUser_id());
            ps.setLong(7, outfit.getEvent_id());
            ps.setLong(8, outfit.getOutfit_id());

            ps.executeUpdate();

            ps.close();
            System.out.println("> L'Outfit : " + outfit.getOutfit_id() + " du  " + outfit.getTimestamp() + " a été mis à jour");



    }


    public List<Outfit> getOutfitsByUser(long id) throws  SQLException{

        //long[] tab = new long[2];
        List<Outfit> outfits = new ArrayList<>();
            Outfit outfit;
            String query = "SELECT outfits.* from outfits, users  WHERE users.user_id= outfits.user_id and users.user_id=?";
            PreparedStatement pstmnt = connection.prepareStatement(query);

            pstmnt.setLong(1, id);
            ResultSet rs = pstmnt.executeQuery();

            while (rs.next()) {
                 outfit = new Outfit(
                        rs.getLong("outfit_id"),
                        rs.getString("timestamp"),
                        rs.getString("description"),
                        rs.getString("photo"),
                        rs.getLong("style_id"),
                        rs.getLong("user_id"),
                        rs.getLong("event_id")
                );
                outfits.add(outfit);
            }

            return outfits;

    }

    public List<Clothe> getClothesOfOutfit(long id) throws  SQLException{

        List<Clothe> clothes = new ArrayList<>();

        Clothe clothe;
        String query = "SELECT clothes . * \n" +
                "FROM clothes\n" +
                "INNER JOIN compose ON clothes.clothe_id = compose.clothe_id\n" +
                "INNER JOIN outfits ON outfits.outfit_id = compose.outfit_id\n" +
                "WHERE outfits.outfit_id =?";
        PreparedStatement pstmnt = connection.prepareStatement(query);

        pstmnt.setLong(1, id);
        ResultSet rs = pstmnt.executeQuery();

        while (rs.next()) {
            clothe = new Clothe(
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


    public static long get_likes(Outfit outfit) throws SQLException{
        PreparedStatement ps =connection.prepareStatement("SELECT (COUNT *) as likes FROM liker where outfit_id = ?");
        ps.setLong(1, outfit.getOutfit_id());

        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getLong("likes");
    }


    public Outfit addSelfieToOutfit(Outfit outfit, MultipartFile selfie) throws SQLException, IOException {
        String name = UUID.randomUUID().toString();
        String path = Outfit.ROOT+"/"+name;
        if (!selfie.isEmpty()) {
            try {
                //Saving the picture in the the ROOT directory
                File outputFile = new File(path);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(outputFile));
                FileCopyUtils.copy(selfie.getInputStream(), stream);
                stream.close();

                //Updating database
                outfit.setPhoto(path);
                OutfitDAO.update_outfit(outfit);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Fichier non trouvé");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outfit;
    }
}
