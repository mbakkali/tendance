package insa.tc.tendance.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import insa.tc.tendance.database.Outfit;

/**
 * Created by patrik on 11/06/16.
 */
public class SelfieDialogBuilder extends AlertDialog.Builder{

    private Uri fileUri;

    protected SelfieDialogBuilder(Context context, final Activity activity, final Outfit outfit) {
        super(context);
        setPositiveButton("Take a selfie",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        outfit.setPath_photo(fileUri.getPath());
                        camera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name
                        // start the Image Capture Intent
                        activity.startActivityForResult(camera, 1); //La photo sera sauvegarder dans fileUri
                    }
                });
    }

}
