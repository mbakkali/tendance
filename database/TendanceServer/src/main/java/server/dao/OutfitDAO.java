package server.dao;

import server.Outfit;
import server.SQLDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OutfitDAO {
    private static Connection connection = SQLDatabase.connectDatabase();

    public static void add_outfit(Outfit outfit) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO outfits (`timestamp`, `description`, `photo`, `style_id`,`likes`) VALUE (?,?,?,?,?)");

            pstmt.setString(1, SQLDatabase.CurrentTimestampToString());
            pstmt.setString(2, outfit.getDescription());
            pstmt.setString(3, outfit.getPhoto());
            pstmt.setLong(4, outfit.getStyle_id());
            pstmt.setLong(5, outfit.getLikes());

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
                        rs.getLong("likes"),
                        rs.getLong("user_id") );
            }
        }
        catch (SQLException e){
            System.out.println("Erreur GetOutfitByID");
        }
        return outfit;
    }



    public static void update_outfit(Outfit outfit) {
        try {

            PreparedStatement ps = connection.prepareStatement("UPDATE Tendance.outfits " +
                    "SET outfits.outfit_id = ?, outfits.timestamp = ?," +
                    "outfits.description = ?, outfits.photo = ?, outfits.style_id = ?, outfits.likes = ? ,outfit.user_id=?" +
                    "WHERE outfits.outfit_id = ?");

            ps.setLong(1, outfit.getOutfit_id());
            ps.setString(2, outfit.getTimestamp());
            ps.setString(3, outfit.getDescription());
            ps.setString(4, outfit.getPhoto());
            ps.setLong(5, outfit.getStyle_id());
            ps.setLong(6, outfit.getLikes());
            ps.setLong(7, outfit.getUser_id());

            ps.setLong(8, outfit.getOutfit_id());

            ps.executeUpdate();

            ps.close();
            System.out.println("> L'Outfit : " + outfit.getOutfit_id() + " du  " + outfit.getTimestamp() + " a été mis à jour");
        }
        catch (SQLException e){
            System.out.println("Erreur UpdateOutfit");
        }


    }


    public static long[] getOutfitsByUser(long id) {

        long[] tab = new long[2];
        try {



            String query = "SELECT outfit_id from outfits, users  WHERE users.user_id= outfits.user_id and users.user_id=?";
            PreparedStatement pstmnt = connection.prepareStatement(query);

            pstmnt.setLong(1, id);
            ResultSet rs = pstmnt.executeQuery();

            ArrayList<Long> list = new ArrayList<Long>();

            while (rs.next()) {

                int i =0;
                long outfitid = rs.getLong("outfit_id");
                list.add(outfitid);
            }

            for(int j=0; j<list.size(); j++) {
                tab[j]= list.get(j) ;
            }

        }
        catch(SQLException e){
            System.out.println("Erreur getOutfitsbyUser");
        }

            return tab;

    }

    public static long get_likes(Outfit outfit) throws SQLException{
        PreparedStatement ps =connection.prepareStatement("SELECT (COUNT *) as likes FROM liker where outfit_id = ?");
        ps.setLong(1, outfit.getOutfit_id());

        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getLong("likes");
    }
}
