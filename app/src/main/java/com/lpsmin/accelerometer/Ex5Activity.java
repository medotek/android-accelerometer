package com.lpsmin.accelerometer;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Ex5Activity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex5);
        startService(new Intent(Ex5Activity.this,ShakeDetectionService.class));
    }
    boolean isSwitchedOn;


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void toggle(View view)
    {
        //ImageButton button = findViewById(R.id.imageButton);
        try{
            if(getTitle().equals(getString(R.string.app_title)))
            {
                setTitle(getString(R.string.app_title).concat(" ON"));
                toggleLED("on");
            }else
            if(getTitle().equals(getString(R.string.app_title).concat(" ON")))
            {
                setTitle(getString(R.string.app_title).concat(" OFF"));
                toggleLED("off");
            }else
            if(getTitle().equals(getString(R.string.app_title).concat(" OFF")))
            {
                setTitle(getString(R.string.app_title).concat(" ON"));
                toggleLED("on");
            }
        }catch (Exception e)
        {
            return;
        }
        return;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void toggleLED(String str) throws CameraAccessException {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String cameraId = null;


            if(cameraManager!=null)
            {
                cameraId = cameraManager.getCameraIdList()[0];
            }

            if(cameraManager!=null)
            {
                if(str.equals("on"))
                {
                    cameraManager.setTorchMode(cameraId,true);
                    isSwitchedOn = true;
                }
                else
                {
                    cameraManager.setTorchMode(cameraId,false);
                    isSwitchedOn = false;
                }
            }
        }else
        {
            Toast.makeText(this, "Lower API Version then 23!\n  App: Not of Use for You", Toast.LENGTH_SHORT).show();
        }
    }
    public void returntoPrevAct(View view) {
        finish();
    }
}
