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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import insa.tc.tendance.camera.SelfieFile;
import insa.tc.tendance.database.Clothe;
import insa.tc.tendance.database.Outfit;
import insa.tc.tendance.database.User;
import insa.tc.tendance.dialog.AddClotheDialogFragment;
import insa.tc.tendance.dialog.DescriptionDialogBuilder;
import insa.tc.tendance.dialog.SelfieDialogFragment;
import insa.tc.tendance.dialog.SendOutfitDialogBuilder;
import insa.tc.tendance.requests.GetSuggestion;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

/**
 * Created by cemonet on 07/05/2016.
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
        final String name = user.getUsername();

        home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(DressingActivity.this, ActualiteActivity.class);
                user.putUserIntoIntent(home);
                startActivity(home);
            }
        });

        calendar = (ImageButton) findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendrier = new Intent(DressingActivity.this, CalendarActivity.class);
                user.putUserIntoIntent(calendrier);
                startActivity(calendrier);
            }
        });

        tshirt = (ImageButton) findViewById(R.id.tshirt);
        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tshirt = new Intent(DressingActivity.this, DressingActivity.class);
                user.putUserIntoIntent(tshirt);
                startActivity(tshirt);
            }
        });

        friend = (ImageButton) findViewById(R.id.friend);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent friend = new Intent(DressingActivity.this, FriendActivity.class);
                user.putUserIntoIntent(friend);
                startActivity(friend);
            }
        });

        me = (ImageButton) findViewById(R.id.me);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent personnel = new Intent(DressingActivity.this, PersonnelActivity.class);
                user.putUserIntoIntent(personnel);
                startActivity(personnel);
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
                if (name.equals("cemonet")){
                    showCoat();
                } if (name.equals("pfortier")){
                    hshowCoat();
                } else {
                    eshowCoat();
                }

            }
        });

        top = (Button) findViewById(R.id.top);
        top.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (name.equals("cemonet")){
                    showTop();
                } if (name.equals("pfortier")){
                    hshowTop();
                } else {
                    eshowTop();
                }
            }
        });

        trousers = (Button) findViewById(R.id.trousers);
        trousers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (name.equals("cemonet")){
                    showTrousers();
                } if (name.equals("pfortier")){
                    hshowTrousers();
                } else {
                    eshowTrousers();
                }
            }
        });

        dress = (Button) findViewById(R.id.dress);
        dress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (name.equals("cemonet")){
                    showDress();
                } if (name.equals("pfortier")){
                    hshowDress();
                } else {
                    eshowDress();
                }
            }
        });

        skirt = (Button) findViewById(R.id.skirt);
        skirt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (name.equals("cemonet")){
                    showSkirt();
                } if (name.equals("pfortier")){
                    hshowSkirt();
                } else {
                    eshowSkirt();
                }
            }
        });

        shoes = (Button) findViewById(R.id.shoes);
        shoes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (name.equals("cemonet")){
                    showShoes();
                } if (name.equals("pfortier")){
                    hshowShoes();
                } else {
                    eshowShoes();
                }
            }
        });

        other = (Button) findViewById(R.id.other);
        other.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (name.equals("cemonet")){
                    showOther();
                } if (name.equals("pfortier")){
                    hshowOther();
                } else {
                    eshowOther();
                }
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

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);//largeur, hauteur
        ScrollView scroller = new ScrollView(this);
        scroller.setBackgroundColor(Color.WHITE);
        scroller.setLayoutParams(params3);

        LinearLayout layoutVet = new LinearLayout(this);
        layoutVet.setOrientation(LinearLayout.VERTICAL);
        layoutVet.setBackgroundColor(Color.WHITE);

        final ImageButton coat1 = new ImageButton(this);
        coat1.setImageResource(R.drawable.dcoat1);
        coat1.setBackgroundColor(Color.WHITE);
        final ImageView coatAdd1 = new ImageView (this);
        coat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Coat sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                coatAdd1.setImageResource(R.drawable.dcoat1);
                layoutOutfit.addView(coatAdd1);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        coatAdd1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                coatAdd1.setImageDrawable(null);
                return true;
            }
        });

        ImageButton coat2 = new ImageButton(this);
        coat2.setImageResource(R.drawable.dcoat2);
        coat2.setBackgroundColor(Color.WHITE);
        final ImageView coatAdd2 = new ImageView (this);
        coat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Coat sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                coatAdd2.setImageResource(R.drawable.dcoat2);
                layoutOutfit.addView(coatAdd2);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        coatAdd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                coatAdd2.setImageDrawable(null);
                return true;
            }
        });


        ImageButton coat3 = new ImageButton(this);
        coat3.setImageResource(R.drawable.dcoat3);
        coat3.setBackgroundColor(Color.WHITE);
        final ImageView coatAdd3 = new ImageView (this);
        coat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Coat sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                coatAdd3.setImageResource(R.drawable.dcoat3);
                layoutOutfit.addView(coatAdd3);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        coatAdd3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                coatAdd3.setImageDrawable(null);
                return true;
            }
        });

        layoutVet.addView(coat1);
        layoutVet.addView(coat2);
        layoutVet.addView(coat3);

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

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);//largeur, hauteur
        ScrollView scroller = new ScrollView(this);
        scroller.setBackgroundColor(Color.WHITE);
        scroller.setLayoutParams(params3);

        LinearLayout layoutVet = new LinearLayout(this);
        layoutVet.setOrientation(LinearLayout.VERTICAL);
        layoutVet.setBackgroundColor(Color.WHITE);

        ImageButton top1 = new ImageButton(this);
        top1.setImageResource(R.drawable.dtop1);
        top1.setBackgroundColor(Color.WHITE);
        final ImageView topAdd1 = new ImageView (this);
        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Top sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                topAdd1.setImageResource(R.drawable.dtop1);
                layoutOutfit.addView(topAdd1);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        topAdd1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                topAdd1.setImageDrawable(null);
                return true;
            }
        });

        ImageButton top2 = new ImageButton(this);
        top2.setImageResource(R.drawable.dtop2);
        top2.setBackgroundColor(Color.WHITE);
        final ImageView topAdd2 = new ImageView (this);
        top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Top sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                topAdd2.setImageResource(R.drawable.dtop2);
                layoutOutfit.addView(topAdd2);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        topAdd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                topAdd2.setImageDrawable(null);
                return true;
            }
        });

        ImageButton top3 = new ImageButton(this);
        top3.setImageResource(R.drawable.dtop3);
        top3.setBackgroundColor(Color.WHITE);
        final ImageView topAdd3 = new ImageView (this);
        top3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Top sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                topAdd3.setImageResource(R.drawable.dtop3);
                layoutOutfit.addView(topAdd3);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        topAdd3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                topAdd3.setImageDrawable(null);
                return true;
            }
        });

        ImageButton top4 = new ImageButton(this);
        top4.setImageResource(R.drawable.dtop4);
        top4.setBackgroundColor(Color.WHITE);
        final ImageView topAdd4 = new ImageView (this);
        top4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Top sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                topAdd4.setImageResource(R.drawable.dtop4);
                layoutOutfit.addView(topAdd4);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        topAdd4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                topAdd4.setImageDrawable(null);
                return true;
            }
        });

        layoutVet.addView(top1);
        layoutVet.addView(top2);
        layoutVet.addView(top3);
        layoutVet.addView(top4);

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

        ImageButton trousers1 = new ImageButton(this);
        trousers1.setImageResource(R.drawable.dtrousers1);
        trousers1.setBackgroundColor(Color.WHITE);
        final ImageView trousersAdd1 = new ImageView (this);
        trousers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Pantalon sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                trousersAdd1.setImageResource(R.drawable.dtrousers1);
                layoutOutfit.addView(trousersAdd1);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        trousersAdd1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                trousersAdd1.setImageDrawable(null);
                return true;
            }
        });

        ImageButton trousers2 = new ImageButton(this);
        trousers2.setImageResource(R.drawable.dtrousers2);
        trousers2.setBackgroundColor(Color.WHITE);
        final ImageView trousersAdd2 = new ImageView (this);
        trousers2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Pantalon sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                trousersAdd2.setImageResource(R.drawable.dtrousers2);
                layoutOutfit.addView(trousersAdd2);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        trousersAdd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                trousersAdd2.setImageDrawable(null);
                return true;
            }
        });

        ImageButton trousers3 = new ImageButton(this);
        trousers3.setImageResource(R.drawable.dtrousers3);
        trousers3.setBackgroundColor(Color.WHITE);
        final ImageView trousersAdd3 = new ImageView (this);
        trousers3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Pantalon sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                trousersAdd3.setImageResource(R.drawable.dtrousers3);
                layoutOutfit.addView(trousersAdd3);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        trousersAdd3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                trousersAdd3.setImageDrawable(null);
                return true;
            }
        });

        layoutVet.addView(trousers1);
        layoutVet.addView(trousers2);
        layoutVet.addView(trousers3);

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

        ImageButton skirt1 = new ImageButton(this);
        skirt1.setImageResource(R.drawable.dskirt1);
        skirt1.setBackgroundColor(Color.WHITE);
        final ImageView skirtAdd1 = new ImageView (this);
        skirt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Skirt sélectionnée !",
                        LENGTH_SHORT);
                toast.show();

                skirtAdd1.setImageResource(R.drawable.dskirt1);
                layoutOutfit.addView(skirtAdd1);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        skirtAdd1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                skirtAdd1.setImageDrawable(null);
                return true;
            }
        });


        ImageButton skirt2 = new ImageButton(this);
        skirt2.setImageResource(R.drawable.dskirt2);
        skirt2.setBackgroundColor(Color.WHITE);
        final ImageView skirtAdd2 = new ImageView (this);
        skirt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Dress sélectionnée !",
                        LENGTH_SHORT);
                toast.show();

                skirtAdd2.setImageResource(R.drawable.dskirt2);
                layoutOutfit.addView(skirtAdd2);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        skirtAdd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                skirtAdd2.setImageDrawable(null);
                return true;
            }
        });


        layoutVet.addView(skirt1);
        layoutVet.addView(skirt2);

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

        ImageButton dress1 = new ImageButton(this);
        dress1.setImageResource(R.drawable.ddress1);
        dress1.setBackgroundColor(Color.WHITE);
        final ImageView dressAdd1 = new ImageView (this);
        dress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Dress sélectionnée !",
                        LENGTH_SHORT);
                toast.show();

                dressAdd1.setImageResource(R.drawable.ddress1);
                layoutOutfit.addView(dressAdd1);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        dressAdd1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                dressAdd1.setImageDrawable(null);
                return true;
            }
        });

        ImageButton dress2 = new ImageButton(this);
        dress2.setImageResource(R.drawable.ddress2);
        dress2.setBackgroundColor(Color.WHITE);
        final ImageView dressAdd2 = new ImageView (this);
        dress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Dress sélectionnée !",
                        LENGTH_SHORT);
                toast.show();

                dressAdd2.setImageResource(R.drawable.ddress2);
                layoutOutfit.addView(dressAdd2);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        dressAdd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                dressAdd2.setImageDrawable(null);
                return true;
            }
        });

        ImageButton dress3 = new ImageButton(this);
        dress3.setImageResource(R.drawable.ddress3);
        dress3.setBackgroundColor(Color.WHITE);
        final ImageView dressAdd3 = new ImageView (this);
        dress3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Dress sélectionnée !",
                        LENGTH_SHORT);
                toast.show();

                dressAdd3.setImageResource(R.drawable.ddress1);
                layoutOutfit.addView(dressAdd3);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        dressAdd3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                dressAdd3.setImageDrawable(null);
                return true;
            }
        });

        layoutVet.addView(dress1);
        layoutVet.addView(dress2);
        layoutVet.addView(dress3);

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

        ImageButton shoes1 = new ImageButton(this);
        shoes1.setImageResource(R.drawable.dshoes1);
        shoes1.setBackgroundColor(Color.WHITE);
        final ImageView shoesAdd1 = new ImageView (this);
        shoes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Shoes sélectionnées",
                        LENGTH_SHORT);
                toast.show();

                shoesAdd1.setImageResource(R.drawable.dshoes1);
                layoutOutfit.addView(shoesAdd1);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        shoesAdd1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                shoesAdd1.setImageDrawable(null);
                return true;
            }
        });

        ImageButton shoes2 = new ImageButton(this);
        shoes2.setImageResource(R.drawable.dshoes2);
        shoes2.setBackgroundColor(Color.WHITE);
        final ImageView shoesAdd2 = new ImageView (this);
        shoes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Shoes sélectionnées",
                        LENGTH_SHORT);
                toast.show();

                shoesAdd2.setImageResource(R.drawable.dshoes2);
                layoutOutfit.addView(shoesAdd2);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        shoesAdd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                shoesAdd2.setImageDrawable(null);
                return true;
            }
        });

        ImageButton shoes3 = new ImageButton(this);
        shoes3.setImageResource(R.drawable.dshoes3);
        shoes3.setBackgroundColor(Color.WHITE);
        final ImageView shoesAdd3 = new ImageView (this);
        shoes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Shoes sélectionnées",
                        LENGTH_SHORT);
                toast.show();

                shoesAdd3.setImageResource(R.drawable.dshoes3);
                layoutOutfit.addView(shoesAdd3);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        shoesAdd3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                shoesAdd3.setImageDrawable(null);
                return true;
            }
        });

        ImageButton shoes4 = new ImageButton(this);
        shoes4.setImageResource(R.drawable.dshoes4);
        shoes4.setBackgroundColor(Color.WHITE);
        final ImageView shoesAdd4 = new ImageView (this);
        shoes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Shoes sélectionnées",
                        LENGTH_SHORT);
                toast.show();

                shoesAdd4.setImageResource(R.drawable.dshoes4);
                layoutOutfit.addView(shoesAdd4);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        shoesAdd4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                shoesAdd4.setImageDrawable(null);
                return true;
            }
        });

        layoutVet.addView(shoes1);
        layoutVet.addView(shoes2);
        layoutVet.addView(shoes3);
        layoutVet.addView(shoes4);

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

        ImageButton other1 = new ImageButton(this);
        other1.setImageResource(R.drawable.dother1);
        other1.setBackgroundColor(Color.WHITE);
        final ImageView otherAdd1 = new ImageView (this);
        other1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Accessoire sélectionné",
                        LENGTH_SHORT);
                toast.show();

                otherAdd1.setImageResource(R.drawable.dother1);
                layoutOutfit.addView(otherAdd1);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        otherAdd1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                otherAdd1.setImageDrawable(null);
                return true;
            }
        });

        ImageButton other2 = new ImageButton(this);
        other2.setImageResource(R.drawable.dother2);
        other2.setBackgroundColor(Color.WHITE);
        final ImageView otherAdd2 = new ImageView (this);
        other2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Accessoire sélectionné",
                        LENGTH_SHORT);
                toast.show();

                otherAdd2.setImageResource(R.drawable.dother2);
                layoutOutfit.addView(otherAdd2);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        otherAdd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                otherAdd2.setImageDrawable(null);
                return true;
            }
        });

        ImageButton other3 = new ImageButton(this);
        other3.setImageResource(R.drawable.dother3);
        other3.setBackgroundColor(Color.WHITE);
        final ImageView otherAdd3 = new ImageView (this);
        other3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Accessoire sélectionné",
                        LENGTH_SHORT);
                toast.show();

                otherAdd3.setImageResource(R.drawable.dother3);
                layoutOutfit.addView(otherAdd3);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        otherAdd3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                otherAdd3.setImageDrawable(null);
                return true;
            }
        });

        ImageButton other4 = new ImageButton(this);
        other4.setImageResource(R.drawable.dother4);
        other4.setBackgroundColor(Color.WHITE);
        final ImageView otherAdd4 = new ImageView (this);
        other4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Accessoire sélectionné",
                        LENGTH_SHORT);
                toast.show();

                otherAdd4.setImageResource(R.drawable.dother4);
                layoutOutfit.addView(otherAdd4);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        otherAdd4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                otherAdd4.setImageDrawable(null);
                return true;
            }
        });

        ImageButton other5 = new ImageButton(this);
        other5.setImageResource(R.drawable.dother5);
        other5.setBackgroundColor(Color.WHITE);
        final ImageView otherAdd5 = new ImageView (this);
        other5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Accessoire sélectionné",
                        LENGTH_SHORT);
                toast.show();

                otherAdd5.setImageResource(R.drawable.dother5);
                layoutOutfit.addView(otherAdd5);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        otherAdd5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                otherAdd5.setImageDrawable(null);
                return true;
            }
        });

        layoutVet.addView(other1);
        layoutVet.addView(other2);
        layoutVet.addView(other3);
        layoutVet.addView(other4);
        layoutVet.addView(other5);

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

    private void hshowCoat() {

        final LinearLayout layoutOutfit = (LinearLayout) findViewById(R.id.gauche);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);//largeur, hauteur
        ScrollView scroller = new ScrollView(this);
        scroller.setBackgroundColor(Color.WHITE);
        scroller.setLayoutParams(params3);

        LinearLayout layoutVet = new LinearLayout(this);
        layoutVet.setOrientation(LinearLayout.VERTICAL);
        layoutVet.setBackgroundColor(Color.WHITE);

        final ImageButton coat1 = new ImageButton(this);
        coat1.setImageResource(R.drawable.dhcoat1);
        coat1.setBackgroundColor(Color.WHITE);
        final ImageView coatAdd1 = new ImageView (this);
        coat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Coat sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                coatAdd1.setImageResource(R.drawable.dhcoat1);
                layoutOutfit.addView(coatAdd1);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        coatAdd1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                coatAdd1.setImageDrawable(null);
                return true;
            }
        });

        ImageButton coat2 = new ImageButton(this);
        coat2.setImageResource(R.drawable.dhcoat2);
        coat2.setBackgroundColor(Color.WHITE);
        final ImageView coatAdd2 = new ImageView (this);
        coat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Coat sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                coatAdd2.setImageResource(R.drawable.dhcoat2);
                layoutOutfit.addView(coatAdd2);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        coatAdd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                coatAdd2.setImageDrawable(null);
                return true;
            }
        });


        ImageButton coat3 = new ImageButton(this);
        coat3.setImageResource(R.drawable.dhcoat3);
        coat3.setBackgroundColor(Color.WHITE);
        final ImageView coatAdd3 = new ImageView (this);
        coat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Coat sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                coatAdd3.setImageResource(R.drawable.dhcoat3);
                layoutOutfit.addView(coatAdd3);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        coatAdd3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                coatAdd3.setImageDrawable(null);
                return true;
            }
        });

        ImageButton coat4 = new ImageButton(this);
        coat4.setImageResource(R.drawable.dhcoat4);
        coat4.setBackgroundColor(Color.WHITE);
        final ImageView coatAdd4 = new ImageView (this);
        coat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Coat sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                coatAdd4.setImageResource(R.drawable.dhcoat4);
                layoutOutfit.addView(coatAdd4);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        coatAdd4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                coatAdd4.setImageDrawable(null);
                return true;
            }
        });

        layoutVet.addView(coat1);
        layoutVet.addView(coat2);
        layoutVet.addView(coat3);
        layoutVet.addView(coat4);

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
    private void hshowTop() {

        final LinearLayout layoutOutfit = (LinearLayout) findViewById(R.id.droite);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);//largeur, hauteur
        ScrollView scroller = new ScrollView(this);
        scroller.setBackgroundColor(Color.WHITE);
        scroller.setLayoutParams(params3);

        LinearLayout layoutVet = new LinearLayout(this);
        layoutVet.setOrientation(LinearLayout.VERTICAL);
        layoutVet.setBackgroundColor(Color.WHITE);

        ImageButton top1 = new ImageButton(this);
        top1.setImageResource(R.drawable.dhtop1);
        top1.setBackgroundColor(Color.WHITE);
        final ImageView topAdd1 = new ImageView (this);
        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Top sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                topAdd1.setImageResource(R.drawable.dhtop1);
                layoutOutfit.addView(topAdd1);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        topAdd1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                topAdd1.setImageDrawable(null);
                return true;
            }
        });

        ImageButton top2 = new ImageButton(this);
        top2.setImageResource(R.drawable.dhtop2);
        top2.setBackgroundColor(Color.WHITE);
        final ImageView topAdd2 = new ImageView (this);
        top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Top sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                topAdd2.setImageResource(R.drawable.dhtop2);
                layoutOutfit.addView(topAdd2);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        topAdd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                topAdd2.setImageDrawable(null);
                return true;
            }
        });

        ImageButton top3 = new ImageButton(this);
        top3.setImageResource(R.drawable.dhtop3);
        top3.setBackgroundColor(Color.WHITE);
        final ImageView topAdd3 = new ImageView (this);
        top3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Top sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                topAdd3.setImageResource(R.drawable.dhtop3);
                layoutOutfit.addView(topAdd3);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        topAdd3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                topAdd3.setImageDrawable(null);
                return true;
            }
        });

        ImageButton top4 = new ImageButton(this);
        top4.setImageResource(R.drawable.dhtop4);
        top4.setBackgroundColor(Color.WHITE);
        final ImageView topAdd4 = new ImageView (this);
        top4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Top sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                topAdd4.setImageResource(R.drawable.dhtop4);
                layoutOutfit.addView(topAdd4);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        topAdd4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                topAdd4.setImageDrawable(null);
                return true;
            }
        });

        layoutVet.addView(top1);
        layoutVet.addView(top2);
        layoutVet.addView(top3);
        layoutVet.addView(top4);

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
    private void hshowTrousers() {

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

        ImageButton trousers1 = new ImageButton(this);
        trousers1.setImageResource(R.drawable.dhtrousers1);
        trousers1.setBackgroundColor(Color.WHITE);
        final ImageView trousersAdd1 = new ImageView (this);
        trousers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Pantalon sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                trousersAdd1.setImageResource(R.drawable.dhtrousers1);
                layoutOutfit.addView(trousersAdd1);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        trousersAdd1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                trousersAdd1.setImageDrawable(null);
                return true;
            }
        });

        ImageButton trousers2 = new ImageButton(this);
        trousers2.setImageResource(R.drawable.dhtrousers2);
        trousers2.setBackgroundColor(Color.WHITE);
        final ImageView trousersAdd2 = new ImageView (this);
        trousers2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Pantalon sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                trousersAdd2.setImageResource(R.drawable.dhtrousers2);
                layoutOutfit.addView(trousersAdd2);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        trousersAdd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                trousersAdd2.setImageDrawable(null);
                return true;
            }
        });

        ImageButton trousers3 = new ImageButton(this);
        trousers3.setImageResource(R.drawable.dhtrousers3);
        trousers3.setBackgroundColor(Color.WHITE);
        final ImageView trousersAdd3 = new ImageView (this);
        trousers3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Pantalon sélectionné !",
                        LENGTH_SHORT);
                toast.show();

                trousersAdd3.setImageResource(R.drawable.dhtrousers3);
                layoutOutfit.addView(trousersAdd3);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        trousersAdd3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                trousersAdd3.setImageDrawable(null);
                return true;
            }
        });

        layoutVet.addView(trousers1);
        layoutVet.addView(trousers2);
        layoutVet.addView(trousers3);

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
    private void hshowSkirt() {

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
    private void hshowDress() {
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
    private void hshowShoes() {

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

        ImageButton shoes1 = new ImageButton(this);
        shoes1.setImageResource(R.drawable.dhshoes1);
        shoes1.setBackgroundColor(Color.WHITE);
        final ImageView shoesAdd1 = new ImageView (this);
        shoes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Shoes sélectionnées",
                        LENGTH_SHORT);
                toast.show();

                shoesAdd1.setImageResource(R.drawable.dhshoes1);
                layoutOutfit.addView(shoesAdd1);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        shoesAdd1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                shoesAdd1.setImageDrawable(null);
                return true;
            }
        });

        ImageButton shoes2 = new ImageButton(this);
        shoes2.setImageResource(R.drawable.dhshoes2);
        shoes2.setBackgroundColor(Color.WHITE);
        final ImageView shoesAdd2 = new ImageView (this);
        shoes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Shoes sélectionnées",
                        LENGTH_SHORT);
                toast.show();

                shoesAdd2.setImageResource(R.drawable.dhshoes2);
                layoutOutfit.addView(shoesAdd2);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        shoesAdd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                shoesAdd2.setImageDrawable(null);
                return true;
            }
        });

        ImageButton shoes3 = new ImageButton(this);
        shoes3.setImageResource(R.drawable.dhshoes3);
        shoes3.setBackgroundColor(Color.WHITE);
        final ImageView shoesAdd3 = new ImageView (this);
        shoes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Shoes sélectionnées",
                        LENGTH_SHORT);
                toast.show();

                shoesAdd3.setImageResource(R.drawable.dhshoes3);
                layoutOutfit.addView(shoesAdd3);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        shoesAdd3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                shoesAdd3.setImageDrawable(null);
                return true;
            }
        });

        ImageButton shoes4 = new ImageButton(this);
        shoes4.setImageResource(R.drawable.dhshoes4);
        shoes4.setBackgroundColor(Color.WHITE);
        final ImageView shoesAdd4 = new ImageView (this);
        shoes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Shoes sélectionnées",
                        LENGTH_SHORT);
                toast.show();

                shoesAdd4.setImageResource(R.drawable.dhshoes4);
                layoutOutfit.addView(shoesAdd4);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        shoesAdd4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                shoesAdd4.setImageDrawable(null);
                return true;
            }
        });

        layoutVet.addView(shoes1);
        layoutVet.addView(shoes2);
        layoutVet.addView(shoes3);
        layoutVet.addView(shoes4);

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
    private void hshowOther() {

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

        ImageButton other1 = new ImageButton(this);
        other1.setImageResource(R.drawable.dhother1);
        other1.setBackgroundColor(Color.WHITE);
        final ImageView otherAdd1 = new ImageView (this);
        other1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Accessoire sélectionné",
                        LENGTH_SHORT);
                toast.show();

                otherAdd1.setImageResource(R.drawable.dhother1);
                layoutOutfit.addView(otherAdd1);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        otherAdd1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                otherAdd1.setImageDrawable(null);
                return true;
            }
        });

        ImageButton other2 = new ImageButton(this);
        other2.setImageResource(R.drawable.dhother2);
        other2.setBackgroundColor(Color.WHITE);
        final ImageView otherAdd2 = new ImageView (this);
        other2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Accessoire sélectionné",
                        LENGTH_SHORT);
                toast.show();

                otherAdd2.setImageResource(R.drawable.dhother2);
                layoutOutfit.addView(otherAdd2);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        otherAdd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                otherAdd2.setImageDrawable(null);
                return true;
            }
        });

        ImageButton other3 = new ImageButton(this);
        other3.setImageResource(R.drawable.dhother3);
        other3.setBackgroundColor(Color.WHITE);
        final ImageView otherAdd3 = new ImageView (this);
        other3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Accessoire sélectionné",
                        LENGTH_SHORT);
                toast.show();

                otherAdd3.setImageResource(R.drawable.dhother3);
                layoutOutfit.addView(otherAdd3);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        otherAdd3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                otherAdd3.setImageDrawable(null);
                return true;
            }
        });

        ImageButton other4 = new ImageButton(this);
        other4.setImageResource(R.drawable.dhother4);
        other4.setBackgroundColor(Color.WHITE);
        final ImageView otherAdd4 = new ImageView (this);
        other4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Accessoire sélectionné",
                        LENGTH_SHORT);
                toast.show();

                otherAdd4.setImageResource(R.drawable.dhother4);
                layoutOutfit.addView(otherAdd4);
                //TODO: ajouter un setOnLongClick pour le supprimer
            }
        });
        otherAdd4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast toast = makeText(getApplicationContext(), "Vêtement supprimé !",
                        LENGTH_SHORT);
                toast.show();
                otherAdd4.setImageDrawable(null);
                return true;
            }
        });


        layoutVet.addView(other1);
        layoutVet.addView(other2);
        layoutVet.addView(other3);
        layoutVet.addView(other4);

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

    private void eshowCoat() {

        final LinearLayout layoutOutfit = (LinearLayout) findViewById(R.id.gauche);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);//largeur, hauteur
        ScrollView scroller = new ScrollView(this);
        scroller.setBackgroundColor(Color.WHITE);
        scroller.setLayoutParams(params3);

        LinearLayout layoutVet = new LinearLayout(this);
        layoutVet.setOrientation(LinearLayout.VERTICAL);
        layoutVet.setBackgroundColor(Color.WHITE);


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
    private void eshowTop() {

        final LinearLayout layoutOutfit = (LinearLayout) findViewById(R.id.droite);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);//largeur, hauteur
        ScrollView scroller = new ScrollView(this);
        scroller.setBackgroundColor(Color.WHITE);
        scroller.setLayoutParams(params3);

        LinearLayout layoutVet = new LinearLayout(this);
        layoutVet.setOrientation(LinearLayout.VERTICAL);
        layoutVet.setBackgroundColor(Color.WHITE);

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
    private void eshowTrousers() {

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
    private void eshowSkirt() {

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
    private void eshowDress() {
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
    private void eshowShoes() {

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
    private void eshowOther() {

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
        final CharSequence[] style_radio = {"Casual", "Gala / Cocktails", "Enterrement", "Mariage / Baptême", "Entretien / Reunion", "Soirée Amis"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Suggestion de Look")
                .setItems(style_radio, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = makeText(getApplicationContext(), "style: "+ which,
                                Toast.LENGTH_SHORT);
                        toast.show();
                        int male = 0;
                        if(user.isMale())
                            male = 1;
                        try {
                            List result = new GetSuggestion().execute(male, which).get();
                            showLook(result);
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                        //TODO: appeler fonction Lélé et appeler showLook
                        //1 homme, 0 femme
                    }
                });
        AlertDialog helpDialog = builder.create();
        helpDialog.show();

    }
    private void showLook (List<ArrayList> look){
        AlertDialog.Builder helpBuilder = new android.app.AlertDialog.Builder(this);
        //String lookSug = look.get(0).toString();
        Toast toast = makeText(getApplicationContext(), "showlook: ",
                Toast.LENGTH_SHORT);
        toast.show();
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        for (ArrayList<String> lookSug : look) {
            TextView suggestion = new TextView(this);
            suggestion.setText(lookSug.toString());
            layout.addView(suggestion);
        }

        helpBuilder.setView(layout);

        helpBuilder.setTitle("Voici ta suggestion de look TENDANCE !");
        helpBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        //Close le dialog
                    }
                });

        AlertDialog helpDialog2 = helpBuilder.create();
        helpDialog2.show();

    }
}