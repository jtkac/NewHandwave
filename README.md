# NewHandwave

All Credit for the C files Goes to the original Kritts/Handwave Repo

Handwave was originally intended to be a library that would use a phone's front facing camera to detect a gesture that happens overtop the phone. Generally done with your hand, you can wave your hand up, down, left, right, and further out then towards your phone which registers as a click. The motions are simplified to implement in your project with an interface that may be passed in via parameters. See the usage examples below.

I have made a big update in improving the methodology used in the original library (it is a library now and not a project).
The sensing of motion is now more accurate (opposed to swiping up and it picks up left/right more easily)
I have added convenience methods for initializing and checking permissions > Android M.
I have accounted for OpenCVManager not loading the correct version of OpenCV.

Usage on a seperate project:

1. Download the library to your computer

2. Open the project you want to add this to

3. File -> New -> Import Module

4. Check Both -> OK -> Wait for the libraries to index

5. Right Click your app module -> Open Module Settings

6. Click the dependencies tab 

7. Click the + icon and select the module option

8. Add Both the openCV module and the NewHandwave module

9. The library is now usable in your project (See below for instructions)



Manifest:

      <uses-permission android:name="android.permission.CAMERA" />


Activity/Fragment usage:

            YourActivity implements CameraGestureSensor.Listener, ClickSensor.Listener

                  @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);
                    if (PermissionUtility.checkCameraPermission(this)) {
                        //The third passing in represents a separate click sensor which is not required if you just want the hand motions
                        LocalOpenCV loader = new LocalOpenCV(YourActivity.this, YourActivity.this, YourActivity.this);
                    }
                }

                    @Override
                      public void onResume() {
                          super.onResume();
                          if (PermissionUtility.checkCameraPermission(this)) {
                              LocalOpenCV loader = new LocalOpenCV(YourActivity.this, YourActivity.this, YourActivity.this);
                          }
                      }

Customize the following to correspond to the actions the sensor picks up:

              @Override
                public void onGestureUp(CameraGestureSensor caller, long gestureLength) {
                    Log.i(TAG, "Up");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(YourActivity.this, "Hand Motion Up", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onGestureDown(CameraGestureSensor caller, long gestureLength) {
                    Log.i(TAG, "Down");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(YourActivity.this, "Hand Motion Down", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                @Override
                public void onGestureLeft(CameraGestureSensor caller, long gestureLength) {
                    Log.i(TAG, "Left");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(YourActivity.this, "Hand Motion Left", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onGestureRight(CameraGestureSensor caller, long gestureLength) {
                    Log.i(TAG, "RIght");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(YourActivity.this, "Hand Motion Right", Toast.LENGTH_SHORT).show();
                        }
                    });
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
                
 Enjoy!

Copyright 2014 Krittika D'Silva

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
