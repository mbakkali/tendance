package insa.tc.tendance.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.media.Image;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrik on 18/05/16.
 */
public class Cloth {
    private long id;
    private int type;
    private String s_type; //pour directement afficher le type
    private String pathphoto;
    private Image photo;
    private long owner;





    public Cloth(int type, int owner){
        this.type = type;
        this.owner = owner;
        String photo = "no_picture";
    }

    public Cloth(long id, String type, String photo, long owner){
        this.id = id;
        this.s_type = type;
        this.pathphoto = photo;
        this.owner = owner;
    }
    public Cloth(long id, int type, String photo, long owner){
        this.id = id;
        this.type = type;
        this.pathphoto = photo;
        this.owner = owner;
    }
    public Cloth(int owner){
        this.owner=owner;
    }


    public Drawable getImageFromWeb(){
        //From the web
        try {
            InputStream is = (InputStream) new URL(pathphoto).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public void setPhoto(String photo) {
        this.pathphoto = photo;
    }
    public void setType(int type) {
        this.type = type;
    }


    public void addClothLocalDB(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("type", type);
        values.put("photo", pathphoto);
        values.put("owner", owner);
        id = db.insert("CLOTHES", null, values);
    }

    public List<Cloth> getMyClothRemote(Context context, final User me){
        final ArrayList<Cloth> myclothes = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://serveurTendance.io/myfriends?iduser=" + String.valueOf(me.getId_user());
        JsonObjectRequest jsObj = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("data");
                            for(int i = 0; i < results.length(); i++) {
                                JSONObject cloth = results.getJSONObject(i);
                                myclothes.add(new Cloth(cloth.getLong("id_cloth"), cloth.getString("type"), cloth.getString("photo"), me.getId_user() ) );
                                //
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

        return myclothes;
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
