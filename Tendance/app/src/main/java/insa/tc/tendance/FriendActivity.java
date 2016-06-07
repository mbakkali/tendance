package insa.tc.tendance;

import android.app.Activity;
import android.content.Context;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import insa.tc.tendance.database.User;

/**
 * Created by Camille on 07/05/2016.
 */
public class FriendActivity extends Activity {

    ImageButton home;
    ImageButton calendar;
    ImageButton tshirt;
    ImageButton friend;
    ImageButton me;
    Button testFriend;
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


        //Test Friendlist
        User patrik = new User();
        getFriends(patrik);

        int i = 0;

        testFriend = (Button) findViewById(R.id.friend1);
        testFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent friendProfile = new Intent(FriendActivity.this, FriendProfile.class);
                startActivity(friendProfile);
            }
        });
    }
    public void getFriends(User user){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://90.66.114.198/user/friends" + "?iduser=" + String.valueOf(user.getId_user());
        JsonObjectRequest jsObj = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<User> friends = new ArrayList<>();
                            JSONArray results = response.getJSONArray("");
                            for(int i = 0; i < results.length(); i++) {
                                JSONObject result = results.getJSONObject(i);
                                friends.add(new User(result.getString("username"), result.getString("mail"), result.getBoolean("priv"), result.getString("bio"), result.getBoolean("male"), result.getString("phone")));
                            }
                            afficherFriend(friends);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(jsObj);
    }
    public void afficherFriend(List<User> friends){
        for (User friend: friends) {
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
                    myButton.setText("Go user profile");
                }
            });
        }

    }
}
