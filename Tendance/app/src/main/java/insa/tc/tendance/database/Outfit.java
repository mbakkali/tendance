package insa.tc.tendance.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by patrik on 18/05/16.
 */
public class Outfit {
    private long outfit_id;
    private int user;
    private Date date;
    private String description;
    private int style;
    private String path_photo;



    public Outfit(int user){
        this.user = user;
        this.date = new Date(Calendar.getInstance().getTimeInMillis());
    }
    public Outfit(int user, Date date){
        this.user=user;
        this.date=date;
    }

    public Outfit() {}

    public void addDescription(String description){
        this.description = description;
    }

    public void addDate(Date date){
        this.date=date;
    }

    //Fonction pour avoir le temps correctement parser
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void setPath_photo(String path_photo) {this.path_photo = path_photo;}

    public void setDescription(String description) {this.description = description;}

    public void addOutfitLocalDB(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("p_user", user);
        values.put("date", getDateTime());
        values.put("description", description);
        values.put("style", style);
        values.put("selfie", path_photo);

        outfit_id = db.insert("OUTFITS", null, values);
        //Associate outfit with clothes.
    }

    public boolean hasSelfie(){
        return !(path_photo == null || path_photo.isEmpty());
    }

    public void liked(User target){

    }
    public void unliked(User target){

    }
    public String getDate(){
    return null;
    }
    public int getLike(){
        return 0;
    }
    //TODO FUNCTION FAVOUTFIT
    public Outfit getFavoriteOutfits(){
        return null;
    }

}
