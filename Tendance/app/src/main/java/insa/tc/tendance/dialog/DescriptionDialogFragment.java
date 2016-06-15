package insa.tc.tendance.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.concurrent.ExecutionException;

import insa.tc.tendance.database.Outfit;

public class DescriptionDialogFragment extends DialogFragment {

    private EditText mDescription;
    private Outfit mOutfit;

    public DescriptionDialogFragment() {}

    public DescriptionDialogFragment newInstance(Outfit mOutfit){
        DescriptionDialogFragment fragment = new DescriptionDialogFragment();
        this.mOutfit = mOutfit;
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        mDescription = new EditText(getContext());
        linearLayout.addView(mDescription);
        alertDialogBuilder.setTitle("Description");
        alertDialogBuilder.setPositiveButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return alertDialogBuilder.create();
    }
}
