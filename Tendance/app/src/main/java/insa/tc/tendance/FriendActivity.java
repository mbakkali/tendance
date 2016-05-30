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
import android.widget.Toast;

/**
 * Created by Camille on 07/05/2016.
 */
public class FriendActivity extends Activity {

    ImageButton home;
    ImageButton calendar;
    ImageButton tshirt;
    ImageButton friend;
    ImageButton me;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend);

        home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(FriendActivity.this, ActualiteActivity.class);
                startActivity(home);
            }
        });

        calendar = (ImageButton) findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendrier = new Intent(FriendActivity.this, CalendarActivity.class);
                startActivity(calendrier);
            }
        });

        tshirt = (ImageButton) findViewById(R.id.tshirt);
        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tshirt = new Intent(FriendActivity.this, DressingActivity.class);
                startActivity(tshirt);
            }
        });

        friend = (ImageButton) findViewById(R.id.friend);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent friend = new Intent(FriendActivity.this, FriendActivity.class);
                startActivity(friend);
            }
        });

        me = (ImageButton) findViewById(R.id.me);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(FriendActivity.this, PersonnelActivity.class);
                startActivity(user);
            }
        });
        //TODO Barre de recherge, onChangeListener on Submit listener + appel fonction recherge amis avec le contenue du SearchView.
        //TODO Quand on clic sur un user, on ouvre une nouvelle activité avec le profil d'un ami (ses info,
        //TODO Recupérer les amis: ProfilPicture and Username, id_user
        String amis[] = {"Patoche", "Lele", "Jib", "Mehdi", "Camille","Lulu", "Antoine","Tommy","Théo","Salma","Mathieu"};
        int nbeAmis = amis.length;

        for (int i = 0; i < nbeAmis; i++) {

            final LinearLayout mylayout = new LinearLayout(this);
            final Button myButton = new Button(this);
            final ImageView myPicture = new ImageView (this);

            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params1.gravity = Gravity.CENTER_HORIZONTAL;
            params1.setMargins(0,8,150,0);
            mylayout.setLayoutParams(params1);

            myPicture.setImageResource(R.drawable.androidicon);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(200, 200);
            params2.setMargins(20,0,0,0);
            myPicture.setLayoutParams(params2);

            myButton.setText(amis[i]);
            myButton.setId(i);
            myButton.setAllCaps(false);
            myButton.setBackgroundColor(Color.TRANSPARENT);
            myButton.setTextSize(20);
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params3.gravity = Gravity.CENTER_HORIZONTAL;
            params3.setMargins(8,8,0,0);
            myButton.setLayoutParams(params3);
            final int id_ = myButton.getId();

            mylayout.addView(myPicture);
            mylayout.addView(myButton);

            LinearLayout layout = (LinearLayout) findViewById(R.id.layoutButton);
            layout.addView(mylayout);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    myButton.setText("Go user profile");
                }
            });
        }
    }
}
