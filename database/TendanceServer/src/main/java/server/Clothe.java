package server;

import java.io.File;

/**
 * Created by Patrik on 07/06/2016.
 */
public class Clothe {
    public static String ROOT = "clothes/images";

    private long clothe_id;
    private long owner;
    private long type;
    private String clothe_photo;
    private File photo;


    public Clothe(long clothe_id, long owner, long type, String clothe_photo) {
        this.clothe_id = clothe_id;
        this.owner = owner;
        this.clothe_photo = clothe_photo;
        this.type = type;
    }

    public Clothe(long owner, String clothe_photo) {
        this.owner = owner;
        this.clothe_photo = clothe_photo;
    }

    public long getClothe_id() {return clothe_id;}
    public long getOwner() {return owner;}
    public String getClothe_photo() {return clothe_photo;}
    public void setClothe_id(long clothe_id) {this.clothe_id = clothe_id;}
    public void setPhoto(File photo) {this.photo = photo;}
}
