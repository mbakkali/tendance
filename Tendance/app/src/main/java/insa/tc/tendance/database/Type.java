package insa.tc.tendance.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by patrik on 18/05/16.
 */
public class Type {
    private String nom;

    public Type(String nom){
        this.nom = nom;
    }

    public void addTypeLocal(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("nom", this.nom);
        db.insert("TYPES", null, values);
    }

}
