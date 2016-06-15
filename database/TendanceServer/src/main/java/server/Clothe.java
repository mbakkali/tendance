package server;
//test
import java.io.File;

/**
 * Created by Patrik on 07/06/2016.
 */
public class Clothe {
    public static String ROOT = "images/clothes";

    private long clothe_id;

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    private long user_id;
    private long clothe_type;
    private String clothe_photo;
    private String clothe_timestamp;
    private File photo;



    public Clothe(){}
    public Clothe(long clothe_id, long user_id, long clothe_type, String clothe_photo, String clothe_timestamp) {
        this.clothe_id = clothe_id;
        this.user_id = user_id;
        this.clothe_type = clothe_type;
        this.clothe_photo = clothe_photo;
        this.clothe_timestamp = clothe_timestamp;
    }
    public Clothe(Clothe clothe){
        this.clothe_id=clothe.getClothe_id();
        this.user_id = clothe.getUser_id();
        this.clothe_type = clothe.getClothe_type();
        this.clothe_photo = clothe.getClothe_photo();
        this.clothe_timestamp = clothe.getClothe_timestamp();
    }

    public Clothe(long user_id, long clothe_type) {
        this.user_id = user_id;
        this.clothe_type = clothe_type;
    }

    public Clothe(long user_id, String clothe_photo) {
        this.user_id = user_id;
        this.clothe_photo = clothe_photo;
    }

    public long getClothe_id() {return clothe_id;}
    public long getUser_id() {return user_id;}
    public String getClothe_photo() {return clothe_photo;}
    public void setClothe_id(long clothe_id) {this.clothe_id = clothe_id;}
    public void setPhoto(File photo) {this.photo = photo;}
    public long getClothe_type() {return clothe_type;}
    public String getClothe_timestamp() {return clothe_timestamp;}
    public void setClothe_timestamp(String clothe_timestamp) {this.clothe_timestamp = clothe_timestamp;}

    public void setClothe_photo(String clothe_photo) {
        this.clothe_photo = clothe_photo;
    }

    public File getPhotoUrl() {return photo;}

    public void setClothe_type(long clothe_type) {
        this.clothe_type = clothe_type;
    }
}
