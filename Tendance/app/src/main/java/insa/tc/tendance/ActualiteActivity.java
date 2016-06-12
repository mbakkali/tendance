package insa.tc.tendance;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import insa.tc.tendance.database.User;

/**
 * Created by Camille on 06/05/2016.
 TODO: Récupérer La liste des outfits des amis de l'utilisateur, les trier du plus récent au plus ancient
 Input : ID distant de l'outfit. Photo profil user, username, date de publication, description de l'outfit, image de l'outfit et le selfie(optionelle)
 */

public class ActualiteActivity extends Activity {
    User mUser;
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
        mUser = User.getUserFromIntent(getIntent());

        home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(ActualiteActivity.this, ActualiteActivity.class);
                mUser.putUserIntoIntent(home);
                startActivity(home);
            }
        });

        calendar = (ImageButton) findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendrier = new Intent(ActualiteActivity.this, CalendarActivity.class);
                mUser.putUserIntoIntent(calendrier);
                startActivity(calendrier);
            }
        });

        tshirt = (ImageButton) findViewById(R.id.tshirt);
        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tshirt = new Intent(ActualiteActivity.this, DressingActivity.class);
                mUser.putUserIntoIntent(tshirt);
                startActivity(tshirt);
            }
        });

        friend = (ImageButton) findViewById(R.id.friend);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent friend = new Intent(ActualiteActivity.this, FriendActivity.class);
                mUser.putUserIntoIntent(friend);
                startActivity(friend);
            }
        });

        me = (ImageButton) findViewById(R.id.me);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(ActualiteActivity.this, PersonnelActivity.class);
                mUser.putUserIntoIntent(user);
                startActivity(user);
            }
        });


        like = (ImageButton)findViewById(R.id.like1);
        like.setOnClickListener(new View.OnClickListener() {
            int i =0;

            public void onClick(View v) {

                if (i % 2 == 0){
                    like.setImageResource(R.drawable.heart1);
                    //LIKE
                    i = i + 1;
                } else {
                    like.setImageResource(R.drawable.heart2);
                    //UNLIKE
                    i = i + 1;
                }
            }
        });
        like2 = (ImageButton)findViewById(R.id.like2);
        like2.setOnClickListener(new View.OnClickListener() {
            int i =0;

            public void onClick(View v) {
            //TODO: Ajout des fonctions liked et unliked en fonction de la valeur du like (au chargement)
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