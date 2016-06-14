package insa.tc.tendance.database;

import android.media.Image;

import java.io.File;

/**
 * Created by Patrik on 14/06/2016.
 */
public class ClotheWithFile extends Clothe {
    private File file;

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
}
