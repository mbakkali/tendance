package insa.tc.tendance.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by patrik on 01/06/16.
 */
public class SelfieFile {
    /** Create a file Uri for saving an image or video */
    public static Uri getOutputMediaFileUri(int type, File activity){
        return Uri.fromFile(getOutputMediaFile(type, activity));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type, File activity){
        // To be safe, you should check that the SDCard is mounted
        String dir;
        // using Environment.getExternalStorageState() before doing this.
        switch (type){
            case 1:
                dir = "outfits";
                break;
            case 2:
                dir = "clothes";
                break;
            default:
                dir = "null";
        }

        File mediaStorageDir = new File(activity,dir);// This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("CameraSelfie", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
            UUID.randomUUID().toString() + timeStamp + ".jpg");

        return mediaFile;
    }
}
