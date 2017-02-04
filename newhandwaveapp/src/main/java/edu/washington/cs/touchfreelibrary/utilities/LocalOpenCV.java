package edu.washington.cs.touchfreelibrary.utilities;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import ca.useful.newhandwave.R;
import edu.washington.cs.touchfreelibrary.sensors.CameraGestureSensor;
import edu.washington.cs.touchfreelibrary.sensors.ClickSensor;

public class LocalOpenCV {
    private static final String TAG = "LoadOpenCV";
    private Context context = null;
    private JavaCameraView mCamera = null;
    private CameraGestureSensor mGestureSensor = null;
    private CameraGestureSensor.Listener gestureListener = null;
    private ClickSensor.Listener clickListener = null;
    private BaseLoaderCallback mLoaderCallback = null;

    /**
     * @param context   = Calling activity
     * @param camera    = JavaCameraView object
     * @param gestureListener = have the activity/fragment implement CameraGestureSensor.Listener and pass the instance here
     */
    public LocalOpenCV(Activity context, JavaCameraView camera, CameraGestureSensor.Listener gestureListener) {
        this.context = context;
        this.mCamera = camera;
        this.gestureListener = gestureListener;
        doLoad(context, camera, gestureListener);
    }

    public LocalOpenCV(Activity context, CameraGestureSensor.Listener gestureListener) {
        this.context = context;
        this.gestureListener = gestureListener;
        doLoad(context, gestureListener);
    }

    public LocalOpenCV(Activity context, CameraGestureSensor.Listener gestureListener, ClickSensor.Listener clickListener) {
        this.context = context;
        this.gestureListener = gestureListener;
        this.clickListener = clickListener;
        doLoad(context, gestureListener);
    }

    public void doLoad(Activity context, CameraGestureSensor.Listener listeners) {
        RelativeLayout cameraLayout = new BackendGestureCamera().getJavaCameraViewWrappedInRelativeLayout(context);
        this.mCamera = (JavaCameraView) cameraLayout.findViewById(R.id.camera);
        context.addContentView(cameraLayout, cameraLayout.getLayoutParams());
        makeGenericLoaderCallback();
        loadOpenCV(context);
    }

    public void doLoad(Activity context, CameraGestureSensor.Listener listeners, ClickSensor.Listener clickListener) {
        RelativeLayout cameraLayout = new BackendGestureCamera().getJavaCameraViewWrappedInRelativeLayout(context);
        this.mCamera = (JavaCameraView) cameraLayout.findViewById(R.id.camera);
        context.addContentView(cameraLayout, cameraLayout.getLayoutParams());
        makeGenericLoaderCallback();
        loadOpenCV(context);
    }

    public void doLoad(Activity context, JavaCameraView camera, CameraGestureSensor.Listener listeners) {
        if (PermissionUtility.checkCameraPermission(context)) {
            makeGenericLoaderCallback();
            loadOpenCV(context);
        }

    }

    public void makeGenericLoaderCallback() {
        mLoaderCallback = new BaseLoaderCallback(context) {
            @Override
            public void onManagerConnected(int status) {
                switch (status) {
                    case LoaderCallbackInterface.SUCCESS:
                        mGestureSensor = new CameraGestureSensor(context);

                        mGestureSensor.addGestureListener(gestureListener);
                        if (clickListener != null) {
                            mGestureSensor.addClickListener(clickListener);
                        }
                        mGestureSensor.start(mCamera);

                        break;
                    default:
                        Log.i(TAG, "Some other result than success");
                        break;


                }
            }
        };
    }

    public boolean loadOpenCV(Context context) {
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, context, mLoaderCallback);
            return false;
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
            return true;
        }
    }

    public boolean loadOpenCV(Context context, BaseLoaderCallback loaderCallback) {
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, context, loaderCallback);
            return false;
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            loaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
            return true;
        }
    }

    public BaseLoaderCallback getmLoaderCallback() {
        return mLoaderCallback;
    }
}

