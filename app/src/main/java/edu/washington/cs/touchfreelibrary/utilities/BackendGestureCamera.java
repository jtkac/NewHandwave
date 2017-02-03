package edu.washington.cs.touchfreelibrary.utilities;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.core.Size;

import java.util.List;

import ca.useful.newhandwave.R;

public class BackendGestureCamera {

    public RelativeLayout getJavaCameraViewWrappedInRelativeLayout(Activity context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.item_java_camera_view, null);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(params);
        return layout;
    }
}
