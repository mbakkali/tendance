package insa.tc.tendance.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import insa.tc.tendance.requests.CreateProfilRequest;
import insa.tc.tendance.requests.LoginRequest;


/**
 * Created by patrik on 18/05/16.
 */
public class User implements Serializable{
    private long user_id;
    private String username;
    private String mail;
    private String profilpicture;
    private String age;
    private String bio;
    private boolean male;
    private boolean priv;
    private String phone;
    private String password;


    public User(){
    }

    public User(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }

    public User(String username, String mail, String profilpicture, String age, String bio, boolean male, boolean priv, String phone) {
        this.username = username;
        this.mail = mail;
        this.profilpicture = profilpicture;
        this.age = age;
        this.bio = bio;
        this.male = male;
        this.priv = priv;
        this.phone = phone;
    }

    public User(String username, String mail, String profilpicture, boolean priv, String bio, boolean male, String phone){
        this.username = username;
        this.mail = mail;
        this.bio = bio;
        this.male = male;
        this.priv = priv;
        this.phone = phone;
        this.profilpicture = profilpicture;
    }

    public static User getMyProfil(SQLiteDatabase db, String email){

        //On récupère les infos de l'utilisateur après authentification
        String[] projection = {
                "user_id",
                "username",
                "mail",
                "profilpicture",
                "bio",
                "male",
                "priv",
                "phone"
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
        User me = new User(c.getString(1),c.getString(2),"null",c.getInt(6)==1 ,c.getString(4), c.getInt(5)==1, c.getString(7) );
        me.setUser_id(c.getLong(0));
        c.close();
        return me;

    }
    public long getId_user(){
        return user_id;
    }
    public String getBio() {return bio;}
    public boolean isMale() {return male;}
    public boolean isPriv() {return priv;}
    public String getPhone() {return phone;}
    public String getMail() {return mail;}
    public String getUsername() {return username;}
    public String getAge() {return age;}
    private void setUser_id(long user_id) {
        this.user_id = user_id;
    }
    public String getProfilpicture() {        return profilpicture;    }
    public String getPassword(){return password;}

    public void addUserLocal(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("username", this.username);
        values.put("mail", this.mail);
        values.put("profilpicture", this.profilpicture);
        values.put("bio", this.bio);
        values.put("male", this.male);
        values.put("priv", this.priv);
        values.put("phone", this.phone);
        setUser_id(db.insert("USERS", null, values));
        System.out.println("A user"+ getId_user());
    }
    public void updateUserLocal(SQLiteDatabase db, User modifiedUser){
        ContentValues values = new ContentValues();
        String[] args = {
                String.valueOf(getId_user())
        };
        values.put("username", modifiedUser.getUsername());
        values.put("mail", modifiedUser.getMail());
        //values.put("profil_picture", modifiedUser.profilpicture);
        values.put("bio", modifiedUser.getBio());
        values.put("male", modifiedUser.isMale());
        values.put("priv", modifiedUser.isPriv());
        values.put("phone", modifiedUser.getPhone());
        int result =db.update("USERS", values, "user_id=?", args);
        System.out.println( getId_user() + " Updated..."+ result);
    }
    public ArrayList<Outfit> getFavoriteOutfits(Context context){


        return null;
    }
    public boolean isFriendWith(User target) {
        boolean friend = true;



        return friend;
    }

    public static User createUserRemote(String username, String mail, String password) throws ExecutionException, InterruptedException {
        User user = new User(username,mail,password);
        if((user = new CreateProfilRequest().execute(user).get()) == null){
            throw new RestClientException("Forbidden");
        }
        return user;
    }

    public static User login(String mail, String password) throws InterruptedException, ExecutionException {
        User user;
        if((user = new LoginRequest().execute(mail,password).get()) == null)
            throw new RestClientException("Forbidden");
        return user;
    }
    public static User getUserFromIntent(Intent intent){
        Gson gson = new Gson();
        return gson.fromJson(intent.getStringExtra("user"), User.class);
    }
    public void putUserIntoIntent(Intent intent){
        intent.putExtra("user", new Gson().toJson(this));
    }
    public static User getFriendFromIntent(Intent intent){
        Gson gson = new Gson();
        return gson.fromJson(intent.getStringExtra("friend"), User.class);
    }
}
