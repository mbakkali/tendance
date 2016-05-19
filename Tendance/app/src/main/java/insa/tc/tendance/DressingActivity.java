package insa.tc.tendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by Camille on 07/05/2016.
 */
public class DressingActivity extends Activity {

    ImageButton home;
    ImageButton calendar;
    ImageButton tshirt;
    ImageButton friend;
    ImageButton me;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dressing);

        home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(DressingActivity.this, ActualiteActivity.class);
                startActivity(home);
            }
        });

        calendar = (ImageButton) findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendrier = new Intent(DressingActivity.this, CalendarActivity.class);
                startActivity(calendrier);
            }
        });

        tshirt = (ImageButton) findViewById(R.id.tshirt);
        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tshirt = new Intent(DressingActivity.this, DressingActivity.class);
                startActivity(tshirt);
            }
        });

        friend = (ImageButton) findViewById(R.id.friend);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent friend = new Intent(DressingActivity.this, FriendActivity.class);
                startActivity(friend);
            }
        });

        me = (ImageButton) findViewById(R.id.me);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(DressingActivity.this, PersonnelActivity.class);
                startActivity(user);
            }
        });

        ImageButton showPopUpButton = (ImageButton) findViewById(R.id.description);
        showPopUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showSimplePopUp();
            }
        });
    }
    private void showSimplePopUp() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Outfit");
        helpBuilder.setMessage("Description de la tenue");
        final EditText input = new EditText(this);
        input.setSingleLine();

        LayoutInflater inflater = getLayoutInflater();
        View RadioButtonLayout = inflater.inflate(R.layout.outfitstyle, null);
        helpBuilder.setView(RadioButtonLayout);

        helpBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
}