
package server;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;

import server.dao.ClotheDAO;
import server.dao.OutfitDAO;
import server.dao.UserDAO;
import server.SQLDatabase;

import java.text.*;




public class Main {

    public static void main(String[] args) {

        try {



         /*   for (int i=0; i<10; i++){
                String outfitdesc = "myoutfit numéro "+i;
                String outfitphoto = "/img/photo"+i;
                String outfittime = "2016-06-11 11:12:00";

                Outfit myoutfit = new Outfit(outfittime, outfitdesc,outfitphoto);
                outfitDAO.add_outfit(myoutfit);
            }*/
/*
            Outfit myoutfit2 = new Outfit(2,"2016-06-11 11:12:23","Mon deuxième outfit","/img/monoutfit2",2);
            Outfit myoutfit3 = new Outfit(3,"2016-06-11 11:12:37","Mon troisème outfit","/img/monoutfit3",3);
            Outfit myoutfit4 = new Outfit(4,"2016-06-11 11:12:07","Mon quatrième outfit","/img/monoutfit4",4);*/

/*
            Clothe clothe = new Clothe(3,1,1,"photo",SQLDatabase.CurrentTimestampToString());


            ClotheDAO clotheDAO =new ClotheDAO();
            //clotheDAO.add_clothe(clothe);
            clotheDAO.del_clothe(9);*/





        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    }

