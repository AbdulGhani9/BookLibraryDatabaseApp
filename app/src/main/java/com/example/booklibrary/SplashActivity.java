package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toast.makeText(this, "WELCOME", Toast.LENGTH_SHORT).show();
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);

                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };thread.start();
    }
}