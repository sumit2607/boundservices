package com.sumit.boundservices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Button mbutton , mbutton2, mbutton3;
    public MusicService musicService;
public ServiceConnection serviceConnection = new ServiceConnection() {


    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        MusicService.serviceBinder serviceBinder = (MusicService.serviceBinder) binder;
        musicService = serviceBinder.getBoundService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //BoundService musicService = new BoundService();
        //musicService.play();


        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, serviceConnection , BIND_AUTO_CREATE);

    }

    private void initView() {
        mbutton = findViewById(R.id.button);
        mbutton2 = findViewById(R.id.button2);
        mbutton3 = findViewById(R.id.button3);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Click",Toast.LENGTH_SHORT);
                musicService.play();

            }
        });
        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicService.pause();
            }
        });
        mbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicService.stop();
            }
        });
    }
}