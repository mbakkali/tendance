package insa.tc.tendance.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;

import insa.tc.tendance.database.Outfit;

/**
 * Created by patrik on 12/06/16.
 */
public class SendOutfitDialogBuilder extends AlertDialog.Builder {
    public SendOutfitDialogBuilder(Activity activity, final Outfit outfit) {
        super(activity.getApplicationContext());
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(activity.getApplicationContext());
        LayoutInflater inflater = activity.getLayoutInflater();
        helpBuilder.setPositiveButton("Envoyer",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println(outfit);
                    }
                })
                .setNegativeButton("Annuler",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //close dialog
                            }
                        });

    }
}
