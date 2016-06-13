package insa.tc.tendance;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import insa.tc.tendance.database.TendanceBDDHelper;
import insa.tc.tendance.database.User;

import static android.widget.Toast.*;

/**
 * Created by Camille on 07/05/2016.
 TODO: On charge les information depuis la base de donnée local au téléphone.
 TODO: Bouton valider: On rapelle
 TODO: Bouton déconnexion: Retour vers l'écran mainactivity et Voir comment on gère les données de l'ancien utilisateurs.
 */
public class PersonnelActivity extends Activity {

    ImageButton home;
    ImageButton calendar;
    ImageButton tshirt;
    ImageButton friend;
    ImageButton me;
    Button saveInfo;

    ImageView userPict;
    Button changePict;
    EditText userName;
    EditText biog;
    EditText email;
    EditText tel;
    RadioButton sex;
    Switch publicC;

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TendanceBDDHelper tdh = new TendanceBDDHelper(getApplicationContext());
        SQLiteDatabase db = tdh.getReadableDatabase();
        setContentView(R.layout.personnel);

        //test les noms doivent changer !
        /*User patrik = User.getMyProfil(db,"patoche@insa-lyon.fr");
        System.out.println(patrik.getUsername());
        User patoche = new User("Patrik", "patoche@insa-lyon.fr",  true,"Je suis patoche la brioche", true, "0648966131");
        patrik.updateUserLocal(db, patoche);
        User patrik2 = User.getMyProfil(db,"patoche@insa-lyon.fr");
        System.out.println(patrik2.getUsername());*/
        //

        home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(PersonnelActivity.this, ActualiteActivity.class);
                startActivity(home);
            }
        });

        calendar = (ImageButton) findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendrier = new Intent(PersonnelActivity.this, CalendarActivity.class);
                startActivity(calendrier);
            }
        });

        tshirt = (ImageButton) findViewById(R.id.tshirt);
        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tshirt = new Intent(PersonnelActivity.this, DressingActivity.class);
                startActivity(tshirt);
            }
        });

        friend = (ImageButton) findViewById(R.id.friend);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent friend = new Intent(PersonnelActivity.this, FriendActivity.class);
                startActivity(friend);
            }
        });

        me = (ImageButton) findViewById(R.id.me);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(PersonnelActivity.this, PersonnelActivity.class);
                startActivity(user);
            }
        });


        //Récupérer les infos du user
        TendanceBDDHelper bddH = new TendanceBDDHelper(getApplicationContext() );
        final SQLiteDatabase datab = bddH.getWritableDatabase();

        final User ex = User.getMyProfil(datab,"patoche@insa-lyon.fr");

        String nameUser = ex.getUsername();
        userName =(EditText) findViewById(R.id.userName);
        userName.setText(nameUser);

        String bio = ex.getBio();
        biog = (EditText) findViewById(R.id.biographie);
        biog.setText(bio);

        String userEmail = ex.getMail();
        email =(EditText) findViewById(R.id.mail);
        email.setText(userEmail);

        String phone = ex.getPhone();
        tel = (EditText) findViewById(R.id.tel);
        tel.setText(phone);

        boolean sexe = ex.isMale();
        sex = (RadioButton) findViewById(R.id.homme);
        if (sexe) {
            sex.setChecked(true);
        }

        boolean publique = ex.isPriv();
        publicC = (Switch) findViewById(R.id.switch1);
        if (publique) {
            publicC.setChecked(true);
        }

        saveInfo = (Button) findViewById(R.id.saveModif);
        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Informations enregistrées !",
                        LENGTH_SHORT);
                toast.show();

                String bio = biog.getText().toString();
                String telM = tel.getText().toString();
                String userM = userName.getText().toString();
                boolean sexM = sex.isChecked();
                boolean publicM = publicC.isChecked();
                User UpdatePatoche = new User(userM, ex.getMail(),ex.getProfilpicture(), publicM, bio, sexM, telM);
                ex.updateUserLocal(datab,UpdatePatoche);

            }
        });


    }
    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.userPicture);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }
}