package insa.tc.tendance.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import insa.tc.tendance.R;
import insa.tc.tendance.database.Outfit;


public class DescriptionDialogBuilder extends AlertDialog.Builder{

    public DescriptionDialogBuilder(Context context, Activity activity , final Outfit outfit) {
        super(context);
        this.setTitle("Outfit");

        this.setMessage("Description de la tenue");
        final EditText input = new EditText(context);
        input.setSingleLine();

        final LayoutInflater inflater =  activity.getLayoutInflater();
        View RadioButtonLayout = inflater.inflate(R.layout.outfitstyle, null);
        this.setView(RadioButtonLayout);
        //TODO Sauvegarder la description dans l'objet / BDD
        this.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        outfit.setDescription(input.toString());
                    }
                });
    }
}
