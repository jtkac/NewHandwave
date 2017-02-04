package ca.useful.newhandwave;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import org.opencv.android.JavaCameraView;
import edu.washington.cs.touchfreelibrary.sensors.CameraGestureSensor;
import edu.washington.cs.touchfreelibrary.sensors.ClickSensor;
import edu.washington.cs.touchfreelibrary.utilities.LocalOpenCV;
import edu.washington.cs.touchfreelibrary.utilities.PermissionUtility;

public class MainActivity extends AppCompatActivity implements CameraGestureSensor.Listener, ClickSensor.Listener {
    private static final String TAG = "MainActivity";
    private JavaCameraView mCamera = null;
    CameraGestureSensor mGestureSensor = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        loadOpenCV();
    }


    protected void loadOpenCV() {
        if (PermissionUtility.checkCameraPermission(this)) {
            LocalOpenCV loc = new LocalOpenCV(this, this, this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGestureUp(CameraGestureSensor caller, long gestureLength) {
        Log.i(TAG, "Up");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Hand Motion Up", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onGestureDown(CameraGestureSensor caller, long gestureLength) {
        Log.i(TAG, "Down");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Hand Motion Down", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onGestureLeft(CameraGestureSensor caller, long gestureLength) {
        Log.i(TAG, "Left");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Hand Motion Left", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onGestureRight(CameraGestureSensor caller, long gestureLength) {
        Log.i(TAG, "RIght");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Hand Motion Right", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mGestureSensor != null)
            mGestureSensor.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadOpenCV();

    }

    @Override
    public void onSensorClick(ClickSensor caller) {
        Log.i(TAG, "Click");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "CLICK!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
