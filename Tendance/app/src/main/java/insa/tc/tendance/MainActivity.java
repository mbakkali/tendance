package insa.tc.tendance;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.sql.Date;
import java.util.Calendar;

import insa.tc.tendance.database.TendanceBDDHelper;
import insa.tc.tendance.database.User;

public class MainActivity extends AppCompatActivity {

    Button seConnecter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seConnecter = (Button) findViewById(R.id.connect);
        seConnecter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent actualite = new Intent(MainActivity.this, ActualiteActivity.class);
                //Prédure de login
                //TODO Add authentication method here
                User user = new User();
                boolean login = user.login("pfortier","passowrd");

                actualite.putExtra("user", new Gson().toJson(user));
                if(login) {
                    startActivity(actualite);
                }
                else {

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