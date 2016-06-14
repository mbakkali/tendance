package insa.tc.tendance.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import insa.tc.tendance.ActualiteActivity;
import insa.tc.tendance.MainActivity;
import insa.tc.tendance.R;
import insa.tc.tendance.database.User;

/**
 * Created by Patrik on 14/06/2016.
 */
public class CreateProfilDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {
    private User user;
    public CreateProfilDialogFragment(){}

    public static CreateProfilDialogFragment newInstance(){
        CreateProfilDialogFragment frag = new CreateProfilDialogFragment();
        return frag;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
            CreateProfilDialogListener listener = (CreateProfilDialogListener) getActivity();
            listener.onFinishCreateProfilDialog(user);
            // Close the dialog and return back to the parent activity
            dismiss();
            return true;
        }
        return false;

    }

    public interface CreateProfilDialogListener {
        void onFinishCreateProfilDialog(User user);
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View dialogView = inflater.inflate(R.layout.create_profil, null);
        final EditText mUsername = (EditText) dialogView.findViewById(R.id.new_username);
        final EditText mMail = (EditText) dialogView.findViewById(R.id.new_mail);
        final EditText mPassword = (EditText) dialogView.findViewById(R.id.new_password);
        builder.setView(dialogView)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            user = User.createUserRemote(mUsername.getText().toString(), mMail.getText().toString(), mPassword.getText().toString());
                            System.out.println("USER POST CREATE REMOTE" + user.toString());
                            Intent actualite = new Intent(getActivity(), ActualiteActivity.class);
                            user.putUserIntoIntent(actualite);
                            startActivity(actualite);
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CreateProfilDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
