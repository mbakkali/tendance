package server;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.awt.*;
import java.io.File;

/**
 * Created by Patrik on 14/06/2016.
 */
public class ClotheWithFile extends Clothe {
    private File file;

    public ClotheWithFile(){
    }
    public ClotheWithFile(File file , int type, long owner) {
        super( type,  owner);
        this.file = file;
    }

    @Override
    public String toString() {
        return "ClotheWithFile{" +
                "file=" + file +
                '}';
    }

    public File getFile() {
        return file;
    }
}
