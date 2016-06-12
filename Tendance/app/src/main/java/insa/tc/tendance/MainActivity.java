package insa.tc.tendance;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.concurrent.ExecutionException;

import insa.tc.tendance.database.TendanceBDDHelper;
import insa.tc.tendance.database.User;
import insa.tc.tendance.requests.CreateProfilRequest;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    public static final String SERVEUR_URL = "http://90.66.114.198";

    Button seConnecter = null;
    Button createUser = null;
    Button FB = null;

    EditText mMail;
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FB = (Button) findViewById(R.id.connectFB);
        FB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Connexion via Facebook bientôt disponible !",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        final AlertDialog.Builder error_login = new AlertDialog.Builder(this).setTitle("Erreur de Connexion")
                .setMessage("Utilisateur non reconnu...");
        mMail = (EditText) findViewById(R.id.addMail);
        mMail.setText("patrik@mail.com"); //default
        mPassword = (EditText) findViewById(R.id.mdp);
        seConnecter = (Button) findViewById(R.id.connect);
        seConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualite = new Intent(MainActivity.this, ActualiteActivity.class);
                String mail = mMail.getText().toString();
                String password = mPassword.getText().toString();
                try {
                    User user = User.login(mail,password);
                    user.putUserIntoIntent(actualite);
                    startActivity(actualite);
                } catch (Exception e) {
                    error_login.show();
                }
            }
        });

        final android.app.AlertDialog.Builder helpBuilder = new android.app.AlertDialog.Builder(this);
        final LinearLayout layout = new LinearLayout(this);
        final EditText newUsername = new EditText(this);
        final EditText newMail= new EditText(this);
        final EditText newMDP = new EditText(this);
        final EditText newConfirm = new EditText(this);
        createUser = (Button) findViewById(R.id.CreateNewUser);
        createUser.setOnClickListener(new View.OnClickListener() {
            User newuser = null;
            @Override
            public void onClick(View v) {

                helpBuilder.setTitle("Nouvel utilisateur");

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(350,350);//largeur, hauteur
                layout.setLayoutParams(params);
                layout.setOrientation(LinearLayout.VERTICAL);

                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(700,200);
                params1.setMargins(16,0,0,0);

                newUsername.setHint("Username...");
                newUsername.setLayoutParams(params1);
                newMail.setHint("Mail...");
                newMail.setLayoutParams(params1);
                newMDP.setHint("Password...");
                newMDP.setLayoutParams(params1);
                newConfirm.setHint("Confirm password...");
                newConfirm.setLayoutParams(params1);

                layout.addView(newUsername);
                layout.addView(newMail);
                layout.addView(newMDP);
                layout.addView(newConfirm);
                helpBuilder.setView(layout);

                helpBuilder.setPositiveButton("Création",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println(newUsername.toString());
                                try {
                                    newuser = User.createUserRemote(newUsername.toString(), newMail.toString(), newMDP.toString());
                                } catch (Exception e) {

                                }
                                System.out.println(newuser);
                            }
                        });


                android.app.AlertDialog helpDialog = helpBuilder.create();
                helpDialog.show();

            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //test database
        TendanceBDDHelper tendance = new TendanceBDDHelper(getApplicationContext());
        SQLiteDatabase db = tendance.getWritableDatabase();
    }
}