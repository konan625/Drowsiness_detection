package com.example.Drive_Assist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Homescreen extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int CAMERA_PERMISSION_CODE = 200;

    private ArrayList<Bitmap> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        Button cameraButton = findViewById(R.id.camera);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Homescreen.this, new String[] { Manifest.permission.CAMERA }, CAMERA_PERMISSION_CODE);
                } else {
                    captureImage();
                }
            }
        });
    }

    private void captureImage() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                captureImage();
            } else {
                Toast.makeText(this, "Camera permission is required to use the camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            if (image != null) {
                images.add(image);
                saveImageToFile(image, images.size());
                Toast.makeText(this, "Image captured and saved to file", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveImageToFile(Bitmap image, int index) {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        String filename = "image_" + index + ".png";

        File file = new File(path, filename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//package com.example.Drive_Assist;
//
//        import android.graphics.Bitmap;
//        import android.os.Bundle;
//        import android.os.Handler;
//        import android.os.HandlerThread;
//        import android.os.Looper;
//        import androidx.annotation.NonNull;
//        import androidx.annotation.Nullable;
//        import androidx.appcompat.app.AppCompatActivity;
//        import androidx.core.app.ActivityCompat;
//        import androidx.core.content.ContextCompat;
//        import android.Manifest;
//        import android.content.Intent;
//        import android.content.pm.PackageManager;
//        import android.provider.MediaStore;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.Toast;
//        import java.io.ByteArrayOutputStream;
//
//public class Homescreen extends AppCompatActivity {
//    private static final int CAMERA_REQUEST_CODE = 100;
//    private static final int CAMERA_PERMISSION_CODE = 200;
//
//    private HandlerThread backgroundThread;
//    private Handler backgroundHandler;
//    private final Object lock = new Object();
//
//    private MyMachineLearningModel mlModel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_homescreen);
//
//        Button cameraButton = findViewById(R.id.camera);
//        cameraButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(Homescreen.this, new String[] { Manifest.permission.CAMERA }, CAMERA_PERMISSION_CODE);
//                } else {
//                    startCamera();
//                }
//            }
//        });
//
//        // Initialize your machine learning model
//        mlModel = new MyMachineLearningModel();
//        mlModel.init();
//    }
//
//    private void startCamera() {
//        // Start the background thread to handle the camera operations
//        backgroundThread = new HandlerThread("CameraBackground");
//        backgroundThread.start();
//        backgroundHandler = new Handler(backgroundThread.getLooper());
//
//        // Schedule the camera capture task to run every 5 seconds
//        backgroundHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (lock) {
//                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                        openCamera();
//                    }
//                }
//                // Schedule the next capture task
//                backgroundHandler.postDelayed(this, 5000);
//            }
//        }, 5000);
//    }
//
//    private void stopCamera() {
//        // Stop the camera background thread and handler
//        backgroundThread.quitSafely();
//        try {
//            backgroundThread.join();
//            backgroundThread = null;
//            backgroundHandler = null;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void openCamera() {
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == CAMERA_PERMISSION_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                startCamera();
//            } else {
//                Toast.makeText(this, "Camera permission is required to use the camera", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK &&
