package insa.tc.tendance.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.JsonReader;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by patrik on 18/05/16.
 */
public class User implements Serializable{
    private long id_user;
    private String username;
    private String mail;
    private String profilpicture;
    private String bio;
    private boolean male;
    private boolean publicprofil;
    private String phonenumber;


    public User(){
    }
    public User(String username, String mail, boolean publicprofil, String bio, boolean male, String phonenumber){
        this.username = username;
        this.mail = mail;
        this.bio = bio;
        this.male = male;
        this.publicprofil = publicprofil;
        this.phonenumber = phonenumber;
    }

    public static User getMyProfil(SQLiteDatabase db, String email){

        //On récupère les infos de l'utilisateur après authentification
        String[] projection = {
                "id_user",
                "nom",
                "mail",
                "profil_picture",
                "biographie",
                "male",
                "public",
                "phonenumber"
        };
        String selection = "mail LIKE ?";
        String[] selectionArgs = { email };
        Cursor c = db.query(
                "USERS",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        c.moveToNext();
        User me = new User(c.getString(1),c.getString(2),c.getInt(6)==1 ,c.getString(4), c.getInt(5)==1, c.getString(7) );
        me.setId_user(c.getLong(0));
        c.close();
        return me;

    }
    public long getId_user(){
        return id_user;
    }

    public String getBio() {return bio;}
    public boolean isMale() {return male;}
    public boolean isPublicprofil() {return publicprofil;}
    public String getPhonenumber() {return phonenumber;}
    public String getMail() {return mail;}
    public String getUsername() {return username;}

    public ArrayList<String> getFriends(Context context){
        final ArrayList<String> friends = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://serveurTendance.io/myfriends?iduser=" + String.valueOf(id_user);
        JsonObjectRequest jsObj = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("data");
                            for(int i = 0; i < results.length(); i++) {
                                friends.add((String) results.getJSONObject(i).get("username"));
                            }
                            } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
        });

        return friends;
    }

    private void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public void addUserLocal(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("nom", this.username);
        values.put("mail", this.mail);
        values.put("profil_picture", this.profilpicture);
        values.put("biographie", this.bio);
        values.put("male", this.male);
        values.put("public", this.publicprofil);
        values.put("phonenumber", this.phonenumber);
        setId_user(db.insert("USERS", null, values));
        System.out.println("A user"+ getId_user());
    }

    public void updateUserLocal(SQLiteDatabase db, User modifiedUser){
        ContentValues values = new ContentValues();
        String[] args = {
                String.valueOf(getId_user())
        };
        values.put("nom", modifiedUser.getUsername());
        values.put("mail", modifiedUser.getMail());
        //values.put("profil_picture", modifiedUser.profilpicture);
        values.put("biographie", modifiedUser.getBio());
        values.put("male", modifiedUser.isMale());
        values.put("public", modifiedUser.isPublicprofil());
        values.put("phonenumber", modifiedUser.getPhonenumber());
        int result =db.update("USERS", values, "id_user=?", args);
        System.out.println( getId_user() + " Updated..."+ result);
    }

    public ArrayList<Outfit> getFavoriteOutfits(Context context){
        ArrayList<Outfit> favorites = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://serveurTendance.io/favorite?iduser=" + getId_user(); // Le serveur va enlever le like
        JsonObjectRequest jsObj = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("data");
                            for (int i = 0; i < results.length(); i++) {
                                JSONObject result = results.getJSONObject(i);

                            }
                        } catch (JSONException e) {
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        return favorites;
    }
}
