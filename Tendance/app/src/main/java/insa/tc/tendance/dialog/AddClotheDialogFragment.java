package insa.tc.tendance.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import insa.tc.tendance.camera.SelfieFile;
import insa.tc.tendance.database.Clothe;
import insa.tc.tendance.database.ClotheWithFile;
import insa.tc.tendance.database.TendanceBDDHelper;
import insa.tc.tendance.database.Type;
import insa.tc.tendance.database.User;
import insa.tc.tendance.requests.AddClothRequest;
import insa.tc.tendance.requests.GetTypesRequest;


/**
 * Created by Patrik on 13/06/2016.
 */
public class AddClotheDialogFragment extends DialogFragment {

    private ImageView mImageView;
    private static List<Type> types;
    private Uri fileUri;
    private User owner;

    public AddClotheDialogFragment(){}

    public AddClotheDialogFragment newInstance(User owner){
        AddClotheDialogFragment fragment = new AddClotheDialogFragment();
        this.owner = owner;
        try {
            types = new GetTypesRequest().execute().get();
        } catch (InterruptedException e) {
            TendanceBDDHelper tendance = new TendanceBDDHelper(this.getContext());
            SQLiteDatabase db = tendance.getReadableDatabase();
            //types = Type.getType(db);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    public void setPhotoClothContent(Uri fileUri){
        mImageView.setImageURI(fileUri);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setWeightSum((float) 0.5);
        mImageView = new ImageView(getContext());
        linearLayout.addView(mImageView);
        RadioButton radioButton= null;
        final RadioGroup radioGroup = new RadioGroup(getContext());
        System.out.println(types.getClass());
        for (Type type: types) {
            radioButton = new RadioButton(getContext());
            //radioButton.setText(type.getType_name());
            radioButton.setText(type.getType_name());
            radioGroup.addView(radioButton);
        }

        Button takePicture = new Button(getContext());
        takePicture.setText("Prend donc une photo !");
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileUri = SelfieFile.getOutputMediaFileUri(2, Environment.getExternalStorageDirectory());
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name
                startActivityForResult(camera, 100); //La photo sera sauvegarder dans fileUri
            }
        });
        linearLayout.addView(radioGroup);
        linearLayout.addView(takePicture);
        alertDialogBuilder.setView(linearLayout);
        //confirm button
        alertDialogBuilder.setPositiveButton("Ajouter !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Clothe clothe = new Clothe();
                clothe.setType(radioGroup.getCheckedRadioButtonId());
                System.out.println(radioGroup.getCheckedRadioButtonId());
                //clothe.setOwner();
                ClotheWithFile clotheWithFile = new ClotheWithFile(new File(String.valueOf(fileUri)), clothe.getType(), 1);
                System.out.println(clotheWithFile);
                new AddClothRequest().execute(clotheWithFile);
            }
        });
        return alertDialogBuilder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 100) {
            if(resultCode == Activity.RESULT_OK) {
                setPhotoClothContent(fileUri);
            }
        }
    }
}
