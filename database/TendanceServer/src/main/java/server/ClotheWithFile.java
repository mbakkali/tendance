package server;

import java.io.File;

/**
 * Created by Patrik on 14/06/2016.
 */
public class ClotheWithFile extends Clothe {


    private File file;

    public ClotheWithFile(File file) {
        super();
        this.file = file;
    }

    public ClotheWithFile(){
    }
    public ClotheWithFile(File file , int type, long owner) {
        super( type,  owner);
        this.file = file;
    }


    @Override
    public String toString() {
        return "OutfitWithFile{" +
                "file=" + file +
                '}';
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static void  PutClotheInClotheWithFile(ClotheWithFile clotheWithFile, Clothe clothe, File file){

         clotheWithFile.setClothe_id(clothe.getClothe_id());
         clotheWithFile.setUser_id(clothe.getUser_id());
         clotheWithFile.setClothe_type(clothe.getClothe_type());
         clotheWithFile.setClothe_photo(clothe.getClothe_photo());
         clotheWithFile.setClothe_photo(clothe.getClothe_photo());
         clotheWithFile.setFile(file);

    }
}
