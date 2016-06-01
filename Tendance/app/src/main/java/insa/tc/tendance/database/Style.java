package insa.tc.tendance.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrik on 18/05/16.
 */
public class Style {
    private long id;
    private String nom;

    public Style(String nom){
        this.nom = nom;
    }
    public Style(long id, String nom){
        this.id = id;
        this.nom = nom;
    }
    public void addStyleLocal(SQLiteDatabase db){

        ContentValues values = new ContentValues();
        values.put("nom", this.nom);
        id = db.insert("STYLES", null , values);
    }

    public ArrayList<Style> getRemoteStyles(Context context){
        final ArrayList<Style> styles = new ArrayList<>();
        String url = "http://serveurTendance.io/styles"; // Le serveur va insérer le like
        JsonObjectRequest jsObj = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("data");
                            for (int i = 0; i < results.length(); i++) {
                                JSONObject result = results.getJSONObject(i);
                                Style style = new Style(result.getLong("id"),result.getString("nom"));
                                styles.add(style);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        return styles;
    }
    public void putStylesinDB(SQLiteDatabase db, List<Style> styles){
        for (Style style: styles) {
            //check if the style is not already in the database;
            //add if not in the database
            if(!style.isStyleInSQLite(db))
                style.addStyleLocal(db);
        }
    }

    public static ArrayList<Style> getStyles(SQLiteDatabase db){
        ArrayList<Style> styles = new ArrayList<>();

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
        while ( c.moveToNext()) {
            styles.add(new Style(c.getInt(0),c.getString(1)));
        }
        c.close();
        return styles;
    }


    private boolean isStyleInSQLite(SQLiteDatabase db){
        String[] projection = {
                "id",
                "nom",
        };
        String selection = "nom = ?";
        String[] selectionArgs = {
                nom
        };
        Cursor c = db.query(
                "STYLES",
                 projection,
                selection,
                selectionArgs,
                null,
                null
                ,null
        );
        while (c.moveToNext()){
            if (this.nom == c.getString(1))
                return true;
        }
        return false;
    }
}
