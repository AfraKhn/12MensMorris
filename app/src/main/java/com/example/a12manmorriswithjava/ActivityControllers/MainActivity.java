package com.example.a12manmorriswithjava.ActivityControllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a12manmorriswithjava.R;

public class MainActivity extends AppCompatActivity {
    private Button settingBtn;
    private Button multiBtn;
    private Button singleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        settingBtn = (Button) findViewById(R.id.goToSetting);
        multiBtn = (Button) findViewById(R.id.MultiP);
        singleButton = (Button) findViewById(R.id.singleP);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Setting.class);
                startActivity(intent);
            }
        });
        multiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(v.getContext(), Arena.class);
                startActivity(intent2);
            }
        });
        singleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Single.class);
                startActivity(intent);
            }
        });


    }
}