package insa.tc.tendance.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DescriptionDialogFragment extends DialogFragment implements TextView.OnEditorActionListener{

    private EditText mDescription;
    private RadioGroup mRadiogroup;

    public interface EditDescDialogListener {
        void onFinishEditDialog(String inputText, int selectedRadio);
    }


    public DescriptionDialogFragment() {}

    public DescriptionDialogFragment newInstance(){
        DescriptionDialogFragment fragment = new DescriptionDialogFragment();
        System.out.println("FRAGMENT DESCRIPTION");
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        mDescription = new EditText(getContext());
        mDescription.setSingleLine();
        mDescription.setOnEditorActionListener(this);
        mRadiogroup = new RadioGroup(getContext());
        RadioButton mRadiobutton;
        int i = 0;
        String[] strs = new String[]{"casual", "gala / cocktail", "enterrement", "mariage / baptême","entretien / réunion ", "Soirée entre amis"};
        for (String str: strs) {
            mRadiobutton = new RadioButton(getContext());
            mRadiobutton.setText(str);
            mRadiobutton.setId(i);
            i++;
            mRadiogroup.addView(mRadiobutton);
        }
        linearLayout.addView(mDescription);
        linearLayout.addView(mRadiogroup);
        alertDialogBuilder.setView(linearLayout);
        alertDialogBuilder.setTitle("Description");
        alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return alertDialogBuilder.create();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
            EditDescDialogListener listener = (EditDescDialogListener) getActivity();
            listener.onFinishEditDialog(mDescription.getText().toString(), mRadiogroup.getCheckedRadioButtonId());
            // Close the dialog and return back to the parent activity
            dismiss();
            return true;
        }
        return false;

    }
}
