package insa.tc.tendance;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.text.DynamicLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Camille on 07/05/2016.
 */
public class OutfitView extends Activity {

    ImageButton home;
    ImageButton calendar;
    ImageButton tshirt;
    ImageButton friend;
    ImageButton me;

    Button supp;

    TextView dateView;
    TextView descriptionView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outfitview);

        home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(OutfitView.this, ActualiteActivity.class);
                startActivity(home);
            }
        });

        calendar = (ImageButton) findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendrier = new Intent(OutfitView.this, CalendarActivity.class);
                startActivity(calendrier);
            }
        });

        tshirt = (ImageButton) findViewById(R.id.tshirt);
        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tshirt = new Intent(OutfitView.this, DressingActivity.class);
                startActivity(tshirt);
            }
        });

        friend = (ImageButton) findViewById(R.id.friend);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent friend = new Intent(OutfitView.this, FriendActivity.class);
                startActivity(friend);
            }
        });

        me = (ImageButton) findViewById(R.id.me);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(OutfitView.this, PersonnelActivity.class);
                startActivity(user);
            }
        });

        supp = (Button) findViewById(R.id.supprimer);
        supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supp.setText("Supprimer la tenue");
                // TODO: retourner à l'activite calendrier
            }
        });

        String date= "11/04/16";
        String description ="Blaba blablablabla balabab lababala bablaba balab #style #mode #ootf";

        dateView = (TextView) findViewById(R.id.date);
        dateView.setText(date);

        descriptionView =(TextView) findViewById(R.id.description);
        descriptionView.setText(description);


    }
}
