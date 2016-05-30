package insa.tc.tendance.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by patrik on 18/05/16.
 */
public class Type {
    private long id;
    private String nom;

    public Type(long id, String nom){
        this.id = id;
        this.nom = nom;
    }
    public Type(String nom){
        this.nom = nom;
    }

    public void addTypeLocal(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("nom", this.nom);
        id = db.insert("TYPES", null, values);
    }

    public ArrayList<Type> getType(SQLiteDatabase db){
        ArrayList<Type> types = new ArrayList<>();

        String[] projection = {
                "id",
                "nom"
        };
        String sortOrder = "id DESC";

        Cursor c = db.query(
                "TYPES",
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        while (c.moveToNext()){
            types.add(new Type(c.getLong(0),c.getString(1)));
        }
        c.close();
        return types;
    }

}
