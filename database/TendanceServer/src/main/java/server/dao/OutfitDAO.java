package server.dao;

import server.Outfit;
import server.SQLDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OutfitDAO {
    private Connection connection = SQLDatabase.connectDatabase();
    public void add_outfit(Outfit outfit) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO outfits (`timestamp`, `description`, `style_id`, `likes`) VALUE (?,?,?,?)");

            pstmt.setString(1, outfit.getTimestamp());
            pstmt.setString(2, outfit.getDescription());
            pstmt.setLong(3, outfit.getStyle_id());
            pstmt.setLong(4, outfit.getLikes());

            pstmt.executeUpdate();

            System.out.println("> Outfit : " + outfit.getDescription() + " ajouté à la table Outfit à "+ outfit.getTimestamp());
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void del_outfit(Outfit outfit) throws SQLException {

            String query = "DELETE from outfits WHERE outfit_id=?";
            PreparedStatement pstmnt = connection.prepareStatement(query);

            pstmnt.setLong(1,outfit.getOutfit_id());
            pstmnt.executeUpdate();

            System.out.println("> L'outfit"+outfit.getDescription()+" a été supprimé de la base users");

            pstmnt.close();
    }
    public Outfit getOutfitByID(long id) {
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
                        rs.getLong("likes") );
            }
        }
        catch (SQLException e){
            System.out.println("Erreur GetOutfitByID");
        }
        return outfit;
    }
    public Outfit update_outfit(Outfit outfit) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("UPDATE outfit(``,``,``,``)");

        return outfit;
    }
    public long get_likes(Outfit outfit) throws SQLException{
        PreparedStatement ps =connection.prepareStatement("SELECT (COUNT *) as likes FROM liker where outfit_id = ?");
        ps.setLong(1, outfit.getOutfit_id());

        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getLong("likes");
    }
}
