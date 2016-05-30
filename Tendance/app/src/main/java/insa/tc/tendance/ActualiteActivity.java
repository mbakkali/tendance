package insa.tc.tendance;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Camille on 06/05/2016.
 */
public class ActualiteActivity extends Activity {

    ImageButton home;
    ImageButton calendar;
    ImageButton tshirt;
    ImageButton friend;
    ImageButton me;
    ImageButton like;
    ImageButton like2;
    ImageButton like3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualite);

        //Receive data from previous activity.

        //TODO Qu'est ce qu'on envoie à la prochaine activité?
        home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(ActualiteActivity.this, ActualiteActivity.class);
                startActivity(home);
            }
        });

        calendar = (ImageButton) findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendrier = new Intent(ActualiteActivity.this, CalendarActivity.class);
                startActivity(calendrier);
            }
        });

        tshirt = (ImageButton) findViewById(R.id.tshirt);
        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tshirt = new Intent(ActualiteActivity.this, DressingActivity.class);
                startActivity(tshirt);
            }
        });

        friend = (ImageButton) findViewById(R.id.friend);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent friend = new Intent(ActualiteActivity.this, FriendActivity.class);
                startActivity(friend);
            }
        });

        me = (ImageButton) findViewById(R.id.me);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(ActualiteActivity.this, PersonnelActivity.class);
                startActivity(user);
            }
        });


        like = (ImageButton)findViewById(R.id.like1);
        like.setOnClickListener(new View.OnClickListener() {
            int i =0;

            public void onClick(View v) {

                if (i % 2 == 0){
                    like.setImageResource(R.drawable.heart1);
                    i = i + 1;
                } else {
                    like.setImageResource(R.drawable.heart2);
                    i = i + 1;
                }
            }
        });
        like2 = (ImageButton)findViewById(R.id.like2);
        like2.setOnClickListener(new View.OnClickListener() {
            int i =0;

            public void onClick(View v) {

                if (i % 2 == 0){
                    like2.setImageResource(R.drawable.heart1);
                    i = i + 1;
                } else {
                    like2.setImageResource(R.drawable.heart2);
                    i = i + 1;
                }
            }
        });
        like3 = (ImageButton)findViewById(R.id.like3);
        like3.setOnClickListener(new View.OnClickListener() {
            int i =0;

            public void onClick(View v) {

                if (i % 2 == 0){
                    like3.setImageResource(R.drawable.heart1);
                    i = i + 1;
                } else {
                    like3.setImageResource(R.drawable.heart2);
                    i = i + 1;
                }
            }
        });
    }
}