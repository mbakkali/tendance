package insa.tc.tendance.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by patrik on 18/05/16.
 */
public class Cloth {
    private long id;
    private int type;
    private String photo;
    private int owner;


    public Cloth(int type, int owner){
        this.type = type;
        this.owner = owner;
        String photo = "no_picture";
    }
    public Cloth(int owner){
        this.owner=owner;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void addClothLocalDB(SQLiteOpenHelper tendance){
        SQLiteDatabase db = tendance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type", type);
        values.put("photo", photo);
        values.put("owner", owner);
        id = db.insert("CLOTHES", null, values);
    }
}
