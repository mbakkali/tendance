package server.dao;

import server.Outfit;
import server.SQLDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OutfitDAO {
    private static Connection connection = SQLDatabase.connectDatabase();

    public static void add_outfit(Outfit outfit) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO outfits (`timestamp`, `description`, `style_id`, `likes`) VALUE (?,?,?,?)");

            pstmt.setString(1, outfit.getTimestamp());
            pstmt.setString(2, outfit.getDescription());
            pstmt.setLong(3, outfit.getStyle_id());
            pstmt.setLong(4, outfit.getLikes());


            //execution du statement (requete)
            pstmt.executeUpdate();

            System.out.println("> Outfit : " + outfit.getDescription() + " ajouté à la base users à "+ outfit.getTimestamp());
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Outfit update_outfit(Outfit outfit) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("UPDATE outfit(``,``,``,``)");

        return outfit;
    }

    public static long get_likes(Outfit outfit) throws SQLException{
        PreparedStatement ps =connection.prepareStatement("SELECT (COUNT *) as likes FROM liker where outfit_id = ?");
        ps.setLong(1, outfit.getOutfit_id());

        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getLong("likes");
    }
}
