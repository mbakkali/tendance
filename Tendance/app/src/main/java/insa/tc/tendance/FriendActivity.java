package insa.tc.tendance;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import insa.tc.tendance.database.User;

public class FriendActivity extends Activity {

    ImageButton home;
    ImageButton calendar;
    ImageButton tshirt;
    ImageButton friend;
    ImageButton me;
    Button testFriend;
    private User myself = new User();


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


        //Peupler les amis avec le réseau
        new HttpRequestGetFriend().execute();


    }
    protected void onStart(){
        super.onStart();
    }
    public void afficherFriend(User... friends){
        for (final User friend: friends) {
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

            myButton.setText(friend.getUsername());
            myButton.setId((int) friend.getId_user());
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
                    Intent friendProfile = new Intent(FriendActivity.this, FriendProfile.class);
                    friendProfile.putExtra("user", new Gson().toJson(myself));
                    friendProfile.putExtra("friend", new Gson().toJson(friend));
                    startActivity(friendProfile);
                }
            });
        }

    }
    private class HttpRequestGetFriend extends AsyncTask<Void, Void, User[]> {
        @Override
        protected User[] doInBackground(Void... params) {
            try {
                final String url = "http://90.66.114.198/user/friends?iduser=1";
                final String url_local = "http://192.168.1.13:5000/user/friends?iduser=1"; //Pour quand patrik fais des test chez lui...
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                User[] users = restTemplate.getForObject(url, User[].class);
                return users;

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        protected void onPostExecute(User... users) {
            if(users!=null)
                afficherFriend(users);
        }

    }
}
