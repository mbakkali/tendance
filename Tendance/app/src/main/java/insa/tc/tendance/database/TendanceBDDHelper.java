package insa.tc.tendance.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by patrik on 17/05/16.
 */
public class TendanceBDDHelper extends SQLiteOpenHelper {

    /*
    * Permet la définition de la BDD et la gestion des version de celle ci.
    * */
    private static final String DATABASE_NAME ="TendanceBDD";
    private static final int DATABASE_VERSION = 1;
    //Database schema
    private static final String TABLE_USERS =
            "CREATE TABLE USERS ("  +
            "id_user" + " INTEGER PRIMARY KEY," +
            "nom" + " TEXT," +
            "mail" + " TEXT," +
            "profil_picture" + " TEXT," + //Path to his localprofilepicture
            "biographie" + " TEXT," +
            "gender" + " BOOLEAN," + //false pour homme, true pour femme
            "public" + " BOOLEAN," +
            "phonenumber" + " TEXT" +
            ");";

    private static final String TABLE_OUTFIT = "CREATE TABLE OUTFITS (" +
            "id_outfit" + " INTEGER PRIMARY KEY," +
            "p_user" + " INTEGER," +
            "date" + " TEXT," +
            "description" + " TEXT," +
            "style" + " INTEGER," +
            "selfie" + " TEXT" +
            //Likes est retrouvé via le réseau
            ");";

    private static final String TABLE_TYPE = "CREATE TABLE TYPES (" +
            "id_type" + " INTEGER PRIMARY KEY," +
            "nom" + " TEXT" +
            ");";

    private static final String TABLE_STYLE ="CREATE TABLE STYLES (" +
            "id_style" + " INTEGER PRIMARY KEY," +
            "nom" + " TEXT" +
            ");";

    private static final String TABLE_CLOTH = "CREATE TABLE CLOTHES (" +
            "id_cloth" + " INTEGER PRIMARY KEY," +
            "type" + " INTEGER," +
            "photo" + " TEXT," + //path to picture
            "owner" + " INTEGER," +
            "FOREIGN KEY(type) REFERENCES TYPE(id_type)," +
            "FOREIGN KEY(owner) REFERENCES USERS(id_user)" +
            ");";

    private static final String TABLE_COMPOSE ="CREATE TABLE COMPOSE (" +
            "p_cloth" + " INTEGER," +
            "p_outfit" + " INTEGER," +
            "FOREIGN KEY(p_cloth) REFERENCES CLOTH(id_cloth)," +
            "FOREIGN KEY(p_outfit) REFERENCES CLOTH(p_outfit)," +
            "PRIMARY KEY(p_cloth, p_outfit)" +
            ");";

    private static final String TABLE_RELATION = "CREATE TABLE RELATION (" +
            "user_a" + " INTEGER," +
            "user_b" + " INTEGER," +
            "FOREIGN KEY(user_a) REFERENCES USERS(id_user)," +
            "FOREIGN KEY(user_b) REFERENCES USERS(id_user)," +
            "PRIMARY KEY(user_a, user_b)" +
            ");";


    public TendanceBDDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Si on arrive dans cet état, nous sommes dans la première utilisation de l'application. [FIRSTTIME]
        db.execSQL(TABLE_USERS);
        db.execSQL(TABLE_OUTFIT);
        db.execSQL(TABLE_CLOTH);
        db.execSQL(TABLE_COMPOSE);
        db.execSQL(TABLE_RELATION);
        db.execSQL(TABLE_STYLE);
        db.execSQL(TABLE_TYPE);
        basicconfiguration(db);

    }
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){
        //Nothing for now...
    }
    public void basicconfiguration(SQLiteDatabase db){
        //Cette fonction sert à peupler avec les style et type que l'on souhaite avoir.

        Style worker = new Style("travail");
        worker.addStyleLocal(db);
        Style casual = new Style("detente");
        casual.addStyleLocal(db);
        Style clubbing = new Style("club");
        clubbing.addStyleLocal(db);


        Type coat = new Type("coat");
        coat.addTypeLocal(db);
        Type top = new Type("top");
        top.addTypeLocal(db);
        Type trousers = new Type("trousers");
        trousers.addTypeLocal(db);
        Type dress = new Type("dress");
        dress.addTypeLocal(db);
        Type skirt = new Type("skirt");
        skirt.addTypeLocal(db);
        Type shoes = new Type("shoes");
        shoes.addTypeLocal(db);
        Type other = new Type("other");

        User patoche = new User("Patoche", "patoche@insa-lyon.fr", "Je suis patoche la brioche", true, true, "0648966131");
    }
}