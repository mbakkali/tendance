package insa.tc.tendance.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrik on 18/05/16.
 */
public class Cloth {
    private long id;
    private int type;
    private String photo;
    private long owner;


    public Cloth(int type, int owner){
        this.type = type;
        this.owner = owner;
        String photo = "no_picture";
    }

    public Cloth(long id, int type, String photo, long owner){
        this.id = id;
        this.type = type;
        this.photo = photo;
        this.owner = owner;
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

    public void addClothLocalDB(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("type", type);
        values.put("photo", photo);
        values.put("owner", owner);
        id = db.insert("CLOTHES", null, values);
    }

    public List<Cloth> getMyCloth(User me, SQLiteDatabase db){
        List<Cloth> myclothes = new ArrayList<>();

        String[] projection = {
                "id_cloth",
                "type",
                "photo"
        };
        String selection = "owner LIKE ?";
        String[] selectionArgs = { String.valueOf(me.getId_user()) };
        try (Cursor c = db.query(
                "CLOTHES",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )) {
            while (c.moveToNext()) {
                Cloth cloth = new Cloth(c.getLong(0), c.getInt(1), c.getString(2), me.getId_user());
                myclothes.add(cloth);
            }
        }
        return myclothes;
    }

    public void removeClothLocal(SQLiteDatabase db, Cloth target){
        //Cette méthode est immunisé aux injections SQL;
        String selection = "id_cloth LIKE ?";
        String[] selectionArgs = {String.valueOf(target.id)};
        db.delete("CLOTHES", selection, selectionArgs);
    }
}
