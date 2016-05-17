package insa.tc.tendance.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by patrik on 17/05/16.
 */
public class DictionaryOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TendanceDB";
    private static final String TABLE_USERS =
            "CREATE TABLE USERS ("  +
            "id_user" + " INTEGER PRIMARY KEY," +
            "nom" + " TEXT," +
            "profil_picture" + " TEXT," + //Path to his localprofilepicture
            "biographie" + " TEXT," +
            "gender" + " BOOLEAN," + //false pour homme, true pour femme
            "public" + " BOOLEAN," +
            "phonenumber" + " TEXT" +
            ");";

    private static final String TABLE_TYPE = "CREATE TABLE TYPE (" +
            "id_type" + " INTEGER PRIMARY KEY," +
            "nom" + " TEXT" +
            ");";

    private static final String TABLE_CLOTH = "CREATE TABLE CLOTH (" +
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

    private static final String TABLE_RELATION = "";
    private static final String TABLE_OUTFIT = "";
    private static final String TABLE_STYLE ="";


    DictionaryOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USERS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){}
}