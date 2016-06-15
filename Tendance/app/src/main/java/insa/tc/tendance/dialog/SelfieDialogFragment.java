package insa.tc.tendance.dialog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import insa.tc.tendance.R;
import insa.tc.tendance.camera.SelfieFile;

public class SelfieDialogFragment extends DialogFragment {

    private ImageView mImageView;
    private Button mButton;
    private Uri fileUri;

    public SelfieDialogFragment(){}

    public static SelfieDialogFragment newInstance(String title){
        SelfieDialogFragment frag = new SelfieDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.selfie, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mImageView = (ImageView) view.findViewById(R.id.selfietaken);
        mButton = (Button) view.findViewById(R.id.buttonselfie);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileUri = SelfieFile.getOutputMediaFileUri(3, Environment.getExternalStorageDirectory());
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name

                startActivityForResult(camera, 100); //La photo sera sauvegarder dans fileUri
            }
        });
        getDialog().setTitle("Prend donc la pose !");
    }

    public void setSelfieContent(Uri fileUri){
            mImageView.setImageURI(fileUri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 100) {
            if(resultCode == Activity.RESULT_OK) {
                setSelfieContent(fileUri);
            }
        }
    }

}
