
package server;

import server.dao.ClotheDAO;
import server.dao.OutfitDAO;

import java.io.File;


public class Main {

    public static void main(String[] args) {

        try {
            OutfitDAO o = new OutfitDAO();
            Outfit outfit = OutfitDAO.getOutfitByID(19);
            System.out.println("On selectionne le outfit : " + outfit.getDescription());

            File file = new File("dcoat3.png");
            ClotheDAO c = new ClotheDAO();


            Clothe clothe = c.getClotheById(2);

            ClotheWithFile clotheWithFile = new ClotheWithFile();

            ClotheWithFile.PutClotheInClotheWithFile(clotheWithFile,clothe,file);

            Clothe clothe2 = clotheWithFile;

            System.out.println(clothe2.getClothe_id() +clothe2.getClothe_photo());



           // userDAO.del_user(userA);

            //userDAO.delFriend(userA,userB);





                //System.out.println(c.addPhotoToClothe(clotheWithFile));





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

