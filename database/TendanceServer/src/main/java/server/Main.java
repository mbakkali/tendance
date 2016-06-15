
package server;

import org.springframework.mock.web.MockMultipartFile;
import server.dao.ClotheDAO;
import server.dao.OutfitDAO;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;


public class Main {

    public static void main(String[] args) {

        try {
            OutfitDAO o = new OutfitDAO();
            Outfit outfit = OutfitDAO.getOutfitByID(19);
            System.out.println("On selectionne le outfit : " + outfit.getDescription());

            ClotheDAO c = new ClotheDAO();
            Clothe clothe = c.getClotheById(2);


            File file = new File("dcoat1.png");

            if (file.exists()) {

                FileInputStream fileInputStream = new FileInputStream(file);
                System.out.println("Ok FileInputStream");

                BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
                System.out.println("Ok BufferedInputStream");

                MockMultipartFile m = new MockMultipartFile("name",fileInputStream);
                System.out.println("Ok MockMultipartFile");
                System.out.println(c.addPhotoToClothe(clothe,m));

                System.out.println("Ok addSelfietoDatabase");
            }




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

