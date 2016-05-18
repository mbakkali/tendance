package insa.tc.tendance.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by patrik on 18/05/16.
 */
public class Style {
    private long id;
    private String nom;

    public Style(String nom){
        this.nom = nom;
    }
    public Style(int id, String nom){
        this.id = id;
        this.nom = nom;
    }
    public void addStyleLocal(SQLiteOpenHelper tendance){
        SQLiteDatabase db = tendance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", this.nom);
        id = db.insert("STYLE", null , values);
    }

    public static ArrayList<Style> getStyles(SQLiteOpenHelper tendance){
        ArrayList<Style> styles = new ArrayList<Style>();
        SQLiteDatabase db = tendance.getReadableDatabase();

        String[] projection = {
                "id",
                "nom"
        };
        String sortOrder = "id DESC";

        Cursor c = db.query(
                "STYLES",  // The table to query
                projection,                               // The columns to return
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null,
                null,
                sortOrder                                 // The sort order
        );
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            styles.add(new Style(c.getInt(0),c.getString(1)));
            c.moveToNext();
        }
        return styles;
    }
}
