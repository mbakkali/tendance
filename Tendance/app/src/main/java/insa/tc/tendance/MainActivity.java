package insa.tc.tendance;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import insa.tc.tendance.database.TendanceBDDHelper;
import insa.tc.tendance.database.User;
import insa.tc.tendance.dialog.CreateProfilDialogFragment;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity implements CreateProfilDialogFragment.CreateProfilDialogListener {

    public static final String SERVEUR_URL = "http://192.168.1.21:5000";
    User user;
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
                    user = User.login(mail,password);
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
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                CreateProfilDialogFragment alertDialog = CreateProfilDialogFragment.newInstance();
                alertDialog.show(fm, "create_profil");
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //test database
        TendanceBDDHelper tendance = new TendanceBDDHelper(getApplicationContext());
        SQLiteDatabase db = tendance.getWritableDatabase();
    }

    @Override
    public void onFinishCreateProfilDialog(User user) {
        Intent actualite = new Intent(MainActivity.this, ActualiteActivity.class);
        user.putUserIntoIntent(actualite);
        startActivity(actualite);

    }
}