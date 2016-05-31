package insa.tc.tendance;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Camille on 30/05/2016.
 */
public class FriendProfile extends Activity {

    ImageButton home;
    ImageButton calendar;
    ImageButton tshirt;
    ImageButton friend;
    ImageButton me;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendprofile);

        home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(FriendProfile.this, ActualiteActivity.class);
                startActivity(home);
            }
        });

        calendar = (ImageButton) findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendrier = new Intent(FriendProfile.this, CalendarActivity.class);
                startActivity(calendrier);
            }
        });

        tshirt = (ImageButton) findViewById(R.id.tshirt);
        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tshirt = new Intent(FriendProfile.this, DressingActivity.class);
                startActivity(tshirt);
            }
        });

        friend = (ImageButton) findViewById(R.id.friend);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent friend = new Intent(FriendProfile.this, FriendActivity.class);
                startActivity(friend);
            }
        });

        me = (ImageButton) findViewById(R.id.me);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(FriendProfile.this, PersonnelActivity.class);
                startActivity(user);
            }
        });

        int idOutfit[]={1,2};
        String userName ="Léa";
        String dates [] ={"12/05/16","02/05/16"};
        String descriptions[]={"Style1 blblabla, outfit, blablabla blabalbal #Mode #OOTD","Style2 blblabla, outfit, blablabla blabalbal #Mode #OOTD"};
        int nbeOutfit=idOutfit.length;


        final ImageView userPict = new ImageView(this);
        final TextView user = new TextView (this);
        LinearLayout layoutProfile = new LinearLayout(this);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
        params1.gravity = Gravity.CENTER_HORIZONTAL;
        params1.setMargins(0,16,0,0);
        layoutProfile.setLayoutParams(params1);

        userPict.setImageResource(R.drawable.fakepic);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(400, 400);
        userPict.setLayoutParams(params2);

        user.setText(userName);
        user.setTextColor(Color.BLACK);
        user.setTextSize(34);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(500, 150);
        params3.gravity = Gravity.CENTER_VERTICAL;
        params3.setMargins(20,50,0,0);
        user.setLayoutParams(params3);
        user.setBackgroundColor(Color.WHITE);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutUserFriend);
        layout.addView(layoutProfile);

        layoutProfile.addView(userPict);
        layoutProfile.addView(user);

        for (int i = 0; i < nbeOutfit; i++) {

            final TextView date = new TextView(this);
            final TextView description = new TextView(this);
            final ImageView outfit = new ImageView(this);
            final ImageView selfie = new ImageView(this);

            date.setText(dates[i]);
            date.setTextColor(Color.RED);
            date.setTypeface(null, Typeface.BOLD);
            date.setTextSize(26);
            LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params4.setMargins(150,50,0,0);
            date.setLayoutParams(params4);

            description.setText(descriptions[i]);
            description.setTextSize(22);
            description.setTextColor(Color.BLACK);
            LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params5.setMargins(80,10,0,0);
            description.setLayoutParams(params5);

            LinearLayout.LayoutParams params6 = new LinearLayout.LayoutParams(500, 600);
            params6.setMargins(10,10,0,0);
            outfit.setLayoutParams(params6);
            outfit.setImageResource(R.drawable.outfit1);

            LinearLayout.LayoutParams params7 = new LinearLayout.LayoutParams(500, 600);
            params7.setMargins(550,-550,0,0);
            selfie.setLayoutParams(params7);
            selfie.setImageResource(R.drawable.outfit2);

            layout.addView(date);
            layout.addView(description);
            layout.addView(outfit);
            layout.addView(selfie);


        }
    }
}
