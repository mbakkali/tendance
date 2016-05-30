package insa.tc.tendance.database;

import android.content.Context;
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
    private int id_user;
    private String username;
    private String mail;
    private String profilpicture;
    private String bio;
    private boolean male;
    private boolean publicprofil;
    private String phonenumber;


    public User(){
    }

    public User getMyProfil(){
        User me = new User();
        //On récupère les infos de l'utilisateur après authentification

        return me;
    }
    public int getId_user(){
        return id_user;
    }

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
}
