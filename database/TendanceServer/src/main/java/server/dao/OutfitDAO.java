package server.dao;

import server.Outfit;
import server.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by bakkali on 07/06/16.
 */

public class OutfitDAO {

    public static void add_outfit(Connection connection, Outfit myoutfit)throws SQLException {


        try {


            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO outfits (`timestamp`, `description`, `style_id`, `likes`) VALUE (?,?,?,?)");

            pstmt.setString(1, myoutfit.getTimestamp());
            pstmt.setString(2, myoutfit.getDescription());
            pstmt.setLong(3, myoutfit.getStyle_id());
            pstmt.setLong(4, myoutfit.getLikes());


            //execution du statement (requete)
            pstmt.executeUpdate();

            System.out.println("> Outfit : " + myoutfit.getDescription() + " ajouté à la base users à "+ myoutfit.getTimestamp());
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
