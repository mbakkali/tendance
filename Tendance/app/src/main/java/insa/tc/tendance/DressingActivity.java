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
 * TODO Ajout de vêtement dans la BDD
 */
public class DressingActivity extends Activity {

    ImageButton home;
    ImageButton calendar;
    ImageButton tshirt;
    ImageButton friend;
    ImageButton me;
    ImageButton addButton;
    ImageButton selfie;
    ImageButton sendoutfit;
    ImageButton suggestion;
    Button coat;
    Button top;
    Button trousers;
    Button dress;
    Button skirt;
    Button shoes;
    Button other;

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
                showDescritpion();
            }
        });

        coat = (Button) findViewById(R.id.coat);
        coat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showCoat();
            }
        });

        top = (Button) findViewById(R.id.top);
        top.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showTop();
            }
        });

        trousers = (Button) findViewById(R.id.trousers);
        trousers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showTrousers();
            }
        });

        dress = (Button) findViewById(R.id.dress);
        dress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDress();
            }
        });

        skirt = (Button) findViewById(R.id.skirt);
        skirt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showSkirt();
            }
        });

        shoes = (Button) findViewById(R.id.shoes);
        shoes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showShoes();
            }
        });

        other = (Button) findViewById(R.id.other);
        other.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showOther();
            }
        });

        addButton = (ImageButton) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdd();
            }
        });

        selfie = (ImageButton) findViewById(R.id.photogr);
        selfie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Intent camera = new Intent(DressingActivity.this, CameraActivity.class);
                //Surement faire passer l'utilisateur courant ou sauvegarder ce truc
                //startActivity(camera);
                //showTakeSelfie();
            }
        });

        sendoutfit = (ImageButton) findViewById(R.id.sendoutfit);
        sendoutfit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showSend();
            }
        });

        suggestion = (ImageButton) findViewById(R.id.questiontshirt);
        suggestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showSuggestion();
            }
        });
    }
    private void showDescritpion() {
        //TODO RECURER LA DESCRIPTION DE L'OUTFIT LOCALE dans un objet
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Outfit");
        helpBuilder.setMessage("Description de la tenue");
        final EditText input = new EditText(this);
        input.setSingleLine();

        LayoutInflater inflater = getLayoutInflater();
        View RadioButtonLayout = inflater.inflate(R.layout.outfitstyle, null);
        helpBuilder.setView(RadioButtonLayout);
        //TODO Sauvegarder la description dans l'objet / BDD
        helpBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
    private void showCoat() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Coat");
        helpBuilder.setMessage("Choisis ton manteau, veste, etc");

        LayoutInflater inflater = getLayoutInflater();
        View affichageLayout = inflater.inflate(R.layout.affichagestyle, null);
        helpBuilder.setView(affichageLayout);

        helpBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
    private void showTop() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Top");
        helpBuilder.setMessage("Choisis ton top");

        LayoutInflater inflater = getLayoutInflater();
        View affichageLayout = inflater.inflate(R.layout.affichagestyle, null);
        helpBuilder.setView(affichageLayout);

        helpBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
    private void showTrousers() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Trousers");
        helpBuilder.setMessage("Choisis ton pantalon");

        LayoutInflater inflater = getLayoutInflater();
        View affichageLayout = inflater.inflate(R.layout.affichagestyle, null);
        helpBuilder.setView(affichageLayout);

        helpBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
    private void showDress() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Dress");
        helpBuilder.setMessage("Choisis ta robe");

        LayoutInflater inflater = getLayoutInflater();
        View affichageLayout = inflater.inflate(R.layout.affichagestyle, null);
        helpBuilder.setView(affichageLayout);

        helpBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
    private void showSkirt() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Skirt");
        helpBuilder.setMessage("Choisis ta jupe");

        LayoutInflater inflater = getLayoutInflater();
        View affichageLayout = inflater.inflate(R.layout.affichagestyle, null);
        helpBuilder.setView(affichageLayout);

        helpBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
    private void showShoes() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Shoes");
        helpBuilder.setMessage("Choisis ta paire de chaussures");

        LayoutInflater inflater = getLayoutInflater();
        View affichageLayout = inflater.inflate(R.layout.affichagestyle, null);
        helpBuilder.setView(affichageLayout);

        helpBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
    private void showOther() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Other");
        helpBuilder.setMessage("Choisis tes accessoires");

        LayoutInflater inflater = getLayoutInflater();
        View affichageLayout = inflater.inflate(R.layout.affichagestyle, null);
        helpBuilder.setView(affichageLayout);

        helpBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    private void showAdd() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Ajoute un vêtement à ton dressing !");
        helpBuilder.setMessage("Quel type de vêtement ?");

        LayoutInflater inflater = getLayoutInflater();
        View affichageLayout = inflater.inflate(R.layout.addvetementstyle, null);
        helpBuilder.setView(affichageLayout);

        helpBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    private void showTakeSelfie() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        //TODO Activité Camera !
        helpBuilder.setPositiveButton("Take a selfie",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    private void showSend() {
        //TODO ajouter fonction addOutfitans BDD Internet ET externe (Regarder comment créer la représentation de l'image)
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        helpBuilder.setPositiveButton("Send Outfit",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    private void showSuggestion() {
        //TODO Ajout fonction Demander une suggestion et afficher la suggestion.
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Suggestion de Look");
        helpBuilder.setMessage("Quel type de Look aujourd'hui ?");

        LayoutInflater inflater = getLayoutInflater();
        View affichageLayout = inflater.inflate(R.layout.suggestionstyle, null);
        helpBuilder.setView(affichageLayout);

        helpBuilder.setPositiveButton("Suggestion !",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
}