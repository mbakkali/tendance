package insa.tc.tendance.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by patrik on 18/05/16.
 */
public class Outfit {
    private long id;
    private int user;
    private Date date;
    private String description;
    private int style;
    private String photo;
    private long likes;

    public Outfit(int user){
        this.user = user;
        this.date = new Date(Calendar.getInstance().getTimeInMillis());
    }
    public Outfit(int user, Date date){
        this.user=user;
        this.date=date;
    }

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


    public void addOutfitLocalDB(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("p_user", user);
        values.put("date", getDateTime());
        values.put("description", description);
        values.put("style", style);
        values.put("selfie", photo);

        id = db.insert("OUTFITS", null, values);
        //Associate outfit with clothes.

    }

    public boolean liked(Context context, User me){
        final boolean[] success = {false};
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://serveurTendance.io/like/"+ id  + "/?iduser=" + me.getId_user(); // Le serveur va insérer le like
        JsonObjectRequest jsObj = new JsonObjectRequest(Request.Method.PUT, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        //Le serveur a répondu à la requête, on peut modifier le coeur
                        success[0] = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        return success[0];
    }
    public boolean unliked(Context context, User me){
        final boolean[] success = {false};
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://serveurTendance.io/like/"+ id  + "/?iduser=" + me.getId_user(); // Le serveur va enlever le like
        JsonObjectRequest jsObj = new JsonObjectRequest(Request.Method.DELETE, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        //Le serveur a répondu à la requête, on peut modifier le coeur
                        success[0] = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        return success[0];
    }

    public String getDate(){
    return null;
    }
    public int getLike(){
        return 0;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }
}
