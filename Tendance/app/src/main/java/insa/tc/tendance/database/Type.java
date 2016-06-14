package insa.tc.tendance.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrik on 18/05/16.
 */
public class Type {
    private long type_id;
    private String type_name;

    public Type(long id, String type_name){
        this.type_id = id;
        this.type_name = type_name;
    }
    public Type(String type_name){
        this.type_name = type_name;
    }

    public void addTypeLocal(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("nom", this.type_name);
        type_id = db.insert("TYPES", null, values);
    }

    public static List<Type> getType(SQLiteDatabase db){
        List<Type> types = new ArrayList<>();

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

    public String getType_name() {return type_name;}
    public long getType_id() {return type_id;}
}
