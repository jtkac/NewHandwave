package edu.washington.cs.touchfreelibrary.utilities;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

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
