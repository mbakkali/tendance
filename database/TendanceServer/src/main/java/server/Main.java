
package server;

import server.dao.ClotheDAO;
import server.dao.OutfitDAO;
import server.dao.UserDAO;


public class Main {

    public static void main(String[] args) {

        try {
            OutfitDAO o = new OutfitDAO();
            Outfit outfit = OutfitDAO.getOutfitByID(19);
            System.out.println("On selectionne le outfit : " + outfit.getDescription());

            ClotheDAO c = new ClotheDAO();
            Clothe clothe = c.getClotheById(2);

            UserDAO userDAO = new UserDAO();
            User userA = userDAO.getUserByID(40);

            userDAO.del_user(userA);

            //userDAO.delFriend(userA,userB);

/*
           File file = new File("dcoat1.png");

            if (file.exists()) {


                System.out.println("Ok MockMultipartFile");
                System.out.println(c.addPhotoToClothe(clothe,m));

                System.out.println("Ok addSelfietoDatabase");
            }*/




/*

            Path path = Paths.get("test.txt");
            String name = "test.txt";
            String originalFileName = "test.txt";
            String contentType = "txt/plain";
            byte[] content = null;
            try {
                content = Files.readAllBytes(path);
            } catch (final IOException e) {
            }
            MultipartFile result = new MockMultipartFile(name,
                    originalFileName, contentType, content);
*/



        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    }

