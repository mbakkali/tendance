package insa.tc.tendance;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.concurrent.ExecutionException;

import insa.tc.tendance.database.TendanceBDDHelper;
import insa.tc.tendance.database.User;

public class MainActivity extends AppCompatActivity {

    Button seConnecter = null;
    EditText mMail;
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AlertDialog.Builder error_login = new AlertDialog.Builder(this).setTitle("Erreur de Connexion")
                .setMessage("Utilisateur non reconnu...");
        mMail = (EditText) findViewById(R.id.addMail);
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
                    user.putUserIntoIntent(getIntent());
                    startActivity(actualite);
                } catch (Exception e) {
                    error_login.show();
                }
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