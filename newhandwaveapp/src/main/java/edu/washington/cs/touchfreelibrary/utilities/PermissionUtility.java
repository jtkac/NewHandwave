package edu.washington.cs.touchfreelibrary.utilities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import edu.washington.cs.touchfreelibrary.sensors.PermissionCodes;

public class PermissionUtility {
    public static boolean checkCameraPermission(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PermissionCodes.CAMERAPERMISSIONCODE);

                return false;
            }
        }
        return true;
    }
}
