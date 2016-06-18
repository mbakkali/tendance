package insa.tc.tendance;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import insa.tc.tendance.database.Outfit;
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

    List<Outfit> outfits;

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

        outfits = new ArrayList<>();

        for (Outfit outfit : outfits) {
            User owner = new User();

            final LinearLayout layout1 = new LinearLayout(this);
            final LinearLayout layout2 = new LinearLayout(this);
            final LinearLayout layout3 = new LinearLayout(this);
            final LinearLayout layout4 = new LinearLayout(this);
            final TextView mUser = new TextView(this);
            final TextView mDate = new TextView(this);
            final TextView mDescr = new TextView(this);
            final ImageView mPictureUser = new ImageView(this);
            final ImageView mtenue = new ImageView(this);
            final ImageView mselfie = new ImageView(this);

            layout1.setOrientation(LinearLayout.VERTICAL);
            layout1.setBackgroundColor(Color.WHITE);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 1200);
            layout1.setLayoutParams(params1);

            layout2.setOrientation(LinearLayout.HORIZONTAL);
            layout2.setBackgroundColor(Color.WHITE);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(600, 300);
            layout2.setLayoutParams(params2);

            layout1.addView(layout2);

            mPictureUser.setImageResource(R.drawable.fakepic);
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(250, 250);
            params3.setMargins(20,0,0,0);
            mPictureUser.setLayoutParams(params3);

            layout2.addView(mPictureUser);

            layout3.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,2000);
            layout3.setLayoutParams(params4);

            layout2.addView(layout3);

            mUser.setText("caca");
            mUser.setTextSize(28);
            mUser.setTextColor(Color.BLACK);
            layout3.addView(mUser);

            mDate.setText("12/12/12");
            mDate.setTextSize(26);
            mDate.setTextColor(Color.RED);
            mDate.setTypeface(Typeface.DEFAULT_BOLD);
            layout3.addView(mDate);

            mDescr.setText("Blabkabka");
            mDescr.setTextSize(18);
            mDescr.setTextColor(Color.BLACK);
            layout1.addView(mDescr);

            layout4.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            layout4.setLayoutParams(params5);

            layout1.addView(layout4);

            mtenue.setImageResource(R.drawable.outfit1);
            LinearLayout.LayoutParams params6 = new LinearLayout.LayoutParams(1000,800);
            params6.setMargins(0,40,0,0);
            layout4.setLayoutParams(params6);
            layout4.addView(mtenue);
            mselfie.setImageResource(R.drawable.outfit2);
            layout4.addView(mselfie);

            LinearLayout layout = (LinearLayout) findViewById(R.id.layoutOutfit);
            layout.addView(layout1);
        }


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