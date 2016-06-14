package insa.tc.tendance;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import insa.tc.tendance.camera.SelfieFile;
import insa.tc.tendance.database.Clothe;
import insa.tc.tendance.database.Outfit;
import insa.tc.tendance.database.User;
import insa.tc.tendance.dialog.AddClotheDialogFragment;
import insa.tc.tendance.dialog.DescriptionDialogBuilder;
import insa.tc.tendance.dialog.SelfieDialogFragment;
import insa.tc.tendance.dialog.SendOutfitDialogBuilder;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

/**
 * Created by Camille on 07/05/2016.
 * TODO Ajout de vêtement dans la BDD
 */
public class DressingActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
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
    Map<String, List<Clothe>> myclothes;
    Dialog descriptionDialog;
    Dialog sendDialog;
    SelfieDialogFragment selfieDialogFragment;
    AddClotheDialogFragment addClotheDialogFragment;
    Outfit outfit = new Outfit();
    User user;
    //create all dialog that we use (might be a horrible way to do it...)

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dressing);

        user = User.getUserFromIntent(getIntent());

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
                showTakeSelfie();

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
        descriptionDialog.show();


    }
    private void showCoat() {

        final LinearLayout layoutOutfit = (LinearLayout) findViewById(R.id.gauche);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,1200);//largeur, hauteur
        ScrollView scroller = new ScrollView(this);
        scroller.setBackgroundColor(Color.WHITE);
        scroller.setLayoutParams(params3);

        LinearLayout layoutVet = new LinearLayout(this);
        layoutVet.setOrientation(LinearLayout.VERTICAL);
        layoutVet.setBackgroundColor(Color.WHITE);

        ImageButton top1 = new ImageButton(this);
        top1.setImageResource(R.drawable.coat1);
        top1.setBackgroundColor(Color.WHITE);
        final ImageView topAdd = new ImageView (this);
        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Coat sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                topAdd.setImageResource(R.drawable.coat1);
                layoutOutfit.addView(topAdd);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });

        ImageButton top2 = new ImageButton(this);
        top2.setImageResource(R.drawable.tshirtd2);
        top2.setBackgroundColor(Color.WHITE);

        ImageButton top3 = new ImageButton(this);
        top3.setImageResource(R.drawable.tshirtd3);
        top3.setBackgroundColor(Color.WHITE);

        layoutVet.addView(top1);
        layoutVet.addView(top2);
        layoutVet.addView(top3);

        scroller.addView(layoutVet);

        layout.addView(scroller);


        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Coat");
        helpBuilder.setView(layout);


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

        final LinearLayout layoutOutfit = (LinearLayout) findViewById(R.id.droite);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,1200);//largeur, hauteur
        ScrollView scroller = new ScrollView(this);
        scroller.setBackgroundColor(Color.WHITE);
        scroller.setLayoutParams(params3);

        LinearLayout layoutVet = new LinearLayout(this);
        layoutVet.setOrientation(LinearLayout.VERTICAL);
        layoutVet.setBackgroundColor(Color.WHITE);

        ImageButton top1 = new ImageButton(this);
        top1.setImageResource(R.drawable.tshirtd1);
        top1.setBackgroundColor(Color.WHITE);
        final ImageView topAdd = new ImageView (this);
        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Top sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                topAdd.setImageResource(R.drawable.tshirtd1);
                layoutOutfit.addView(topAdd);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        topAdd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                topAdd.setImageDrawable(null);
                return true;
            }
        });

        ImageButton top2 = new ImageButton(this);
        top2.setImageResource(R.drawable.tshirtd2);
        top2.setBackgroundColor(Color.WHITE);

        ImageButton top3 = new ImageButton(this);
        top3.setImageResource(R.drawable.tshirtd3);
        top3.setBackgroundColor(Color.WHITE);

        layoutVet.addView(top1);
        layoutVet.addView(top2);
        layoutVet.addView(top3);

        scroller.addView(layoutVet);

        layout.addView(scroller);


        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Top");
        helpBuilder.setView(layout);


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

        final LinearLayout layoutOutfit = (LinearLayout) findViewById(R.id.droite);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,1200);//largeur, hauteur
        ScrollView scroller = new ScrollView(this);
        scroller.setBackgroundColor(Color.WHITE);
        scroller.setLayoutParams(params3);

        LinearLayout layoutVet = new LinearLayout(this);
        layoutVet.setOrientation(LinearLayout.VERTICAL);
        layoutVet.setBackgroundColor(Color.WHITE);

        ImageButton top1 = new ImageButton(this);
        top1.setImageResource(R.drawable.pants1);
        top1.setBackgroundColor(Color.WHITE);
        final ImageView topAdd = new ImageView (this);
        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Pantalon sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                topAdd.setImageResource(R.drawable.pants1);
                layoutOutfit.addView(topAdd);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });

        ImageButton top2 = new ImageButton(this);
        top2.setImageResource(R.drawable.tshirtd2);
        top2.setBackgroundColor(Color.WHITE);

        ImageButton top3 = new ImageButton(this);
        top3.setImageResource(R.drawable.tshirtd3);
        top3.setBackgroundColor(Color.WHITE);

        layoutVet.addView(top1);
        layoutVet.addView(top2);
        layoutVet.addView(top3);

        scroller.addView(layoutVet);

        layout.addView(scroller);


        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Trousers");
        helpBuilder.setView(layout);


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

        final LinearLayout layoutOutfit = (LinearLayout) findViewById(R.id.droite);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,1200);//largeur, hauteur
        ScrollView scroller = new ScrollView(this);
        scroller.setBackgroundColor(Color.WHITE);
        scroller.setLayoutParams(params3);

        LinearLayout layoutVet = new LinearLayout(this);
        layoutVet.setOrientation(LinearLayout.VERTICAL);
        layoutVet.setBackgroundColor(Color.WHITE);

        ImageButton top1 = new ImageButton(this);
        top1.setImageResource(R.drawable.dress1);
        top1.setBackgroundColor(Color.WHITE);
        final ImageView topAdd = new ImageView (this);
        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Dress sélectionnée !",
                        LENGTH_SHORT);
                toast.show();

                topAdd.setImageResource(R.drawable.dress1);
                layoutOutfit.addView(topAdd);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });

        ImageButton top2 = new ImageButton(this);
        top2.setImageResource(R.drawable.tshirtd2);
        top2.setBackgroundColor(Color.WHITE);

        ImageButton top3 = new ImageButton(this);
        top3.setImageResource(R.drawable.tshirtd3);
        top3.setBackgroundColor(Color.WHITE);

        layoutVet.addView(top1);
        layoutVet.addView(top2);
        layoutVet.addView(top3);

        scroller.addView(layoutVet);

        layout.addView(scroller);


        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Dress");
        helpBuilder.setView(layout);


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
        final LinearLayout layoutOutfit = (LinearLayout) findViewById(R.id.droite);
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,1200);//largeur, hauteur
        ScrollView scroller = new ScrollView(this);
        scroller.setBackgroundColor(Color.WHITE);
        scroller.setLayoutParams(params3);

        LinearLayout layoutVet = new LinearLayout(this);
        layoutVet.setOrientation(LinearLayout.VERTICAL);
        layoutVet.setBackgroundColor(Color.WHITE);

        ImageButton top1 = new ImageButton(this);
        top1.setImageResource(R.drawable.skirt1);
        top1.setBackgroundColor(Color.WHITE);
        final ImageView topAdd = new ImageView (this);
        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Skirt sélectionnée !",
                        LENGTH_SHORT);
                toast.show();

                topAdd.setImageResource(R.drawable.skirt1);
                layoutOutfit.addView(topAdd);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });

        ImageButton top2 = new ImageButton(this);
        top2.setImageResource(R.drawable.tshirtd2);
        top2.setBackgroundColor(Color.WHITE);

        ImageButton top3 = new ImageButton(this);
        top3.setImageResource(R.drawable.tshirtd3);
        top3.setBackgroundColor(Color.WHITE);

        layoutVet.addView(top1);
        layoutVet.addView(top2);
        layoutVet.addView(top3);

        scroller.addView(layoutVet);

        layout.addView(scroller);


        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Skirt");
        helpBuilder.setView(layout);


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

        final LinearLayout layoutOutfit = (LinearLayout) findViewById(R.id.droite);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,1200);//largeur, hauteur
        ScrollView scroller = new ScrollView(this);
        scroller.setBackgroundColor(Color.WHITE);
        scroller.setLayoutParams(params3);

        LinearLayout layoutVet = new LinearLayout(this);
        layoutVet.setOrientation(LinearLayout.VERTICAL);
        layoutVet.setBackgroundColor(Color.WHITE);

        ImageButton top1 = new ImageButton(this);
        top1.setImageResource(R.drawable.shoes1);
        top1.setBackgroundColor(Color.WHITE);
        final ImageView topAdd = new ImageView (this);
        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Shoes sélectionnées",
                        LENGTH_SHORT);
                toast.show();

                topAdd.setImageResource(R.drawable.shoes1);
                layoutOutfit.addView(topAdd);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });

        ImageButton top2 = new ImageButton(this);
        top2.setImageResource(R.drawable.tshirtd2);
        top2.setBackgroundColor(Color.WHITE);

        ImageButton top3 = new ImageButton(this);
        top3.setImageResource(R.drawable.tshirtd3);
        top3.setBackgroundColor(Color.WHITE);

        layoutVet.addView(top1);
        layoutVet.addView(top2);
        layoutVet.addView(top3);

        scroller.addView(layoutVet);

        layout.addView(scroller);


        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Shoes");
        helpBuilder.setView(layout);


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

        final LinearLayout layoutOutfit = (LinearLayout) findViewById(R.id.gauche);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,1200);//largeur, hauteur
        ScrollView scroller = new ScrollView(this);
        scroller.setBackgroundColor(Color.WHITE);
        scroller.setLayoutParams(params3);

        LinearLayout layoutVet = new LinearLayout(this);
        layoutVet.setOrientation(LinearLayout.VERTICAL);
        layoutVet.setBackgroundColor(Color.WHITE);

        ImageButton top1 = new ImageButton(this);
        top1.setImageResource(R.drawable.hat1);
        top1.setBackgroundColor(Color.WHITE);
        final ImageView topAdd = new ImageView (this);
        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Accessoire sélectionné",
                        LENGTH_SHORT);
                toast.show();

                topAdd.setImageResource(R.drawable.hat1);
                layoutOutfit.addView(topAdd);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });

        ImageButton top2 = new ImageButton(this);
        top2.setImageResource(R.drawable.tshirtd2);
        top2.setBackgroundColor(Color.WHITE);

        ImageButton top3 = new ImageButton(this);
        top3.setImageResource(R.drawable.tshirtd3);
        top3.setBackgroundColor(Color.WHITE);

        layoutVet.addView(top1);
        layoutVet.addView(top2);
        layoutVet.addView(top3);

        scroller.addView(layoutVet);

        layout.addView(scroller);


        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Accessoires");
        helpBuilder.setView(layout);


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
        System.out.println(user);
        FragmentManager fm = getSupportFragmentManager();
        addClotheDialogFragment = new AddClotheDialogFragment().newInstance(user);
        addClotheDialogFragment.show(fm, "");
/*
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
        helpDialog.show();*/
    }
    private void showTakeSelfie() {
        FragmentManager fm = getSupportFragmentManager();
         selfieDialogFragment = SelfieDialogFragment.newInstance("Selfie");
        selfieDialogFragment.show(fm, "selfie");
    }
    private void showSend() {
        //TODO ajouter fonction addOutfitans BDD Internet ET externe (Regarder comment créer la représentation de l'image)


        sendDialog.show();
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