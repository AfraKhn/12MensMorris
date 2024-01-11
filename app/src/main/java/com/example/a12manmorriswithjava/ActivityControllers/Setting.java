package com.example.a12manmorriswithjava.ActivityControllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.a12manmorriswithjava.Interfaces.ISetting;
import com.example.a12manmorriswithjava.R;

public class Setting extends AppCompatActivity implements ISetting {
    private ImageView backToMain;
    private RadioGroup rg_Difficulty;
    private RadioGroup rg_GameMode;
    private RadioButton selectedRadioButtonDifficulty;
    private RadioButton selectedRadioButtonGameMode;
    private RadioButton Easy, Medium, Hard, Aggressive, Defensive, Normal;
    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        backToMain = (ImageView) findViewById(R.id.backToMain);
        rg_Difficulty = (RadioGroup) findViewById(R.id.rg_Difficulty);
        rg_GameMode = (RadioGroup) findViewById(R.id.rg_GameMode);
        pref = getApplicationContext().getSharedPreferences(PREF, 0);
        String diff = pref.getString(DIFFICULTY_STATE, "");
        String mode = pref.getString(GAME_MODE_STATE, "");
        if (diff.equals("")) {
            rg_Difficulty.check(R.id.Easy);
        } else {
            if (diff.equals("Easy")) {
                rg_Difficulty.check(R.id.Easy);
            }
            if (diff.equals("Medium")) {
                rg_Difficulty.check(R.id.Medium);
            }
            if (diff.equals("Hard")) {
                rg_Difficulty.check(R.id.Hard);
            }
        }
        if (mode.equals("")) {
            rg_GameMode.check(R.id.Aggressive);
        } else {
            if (mode.equals("Aggressive")) {
                rg_GameMode.check(R.id.Aggressive);
            }
            if (mode.equals("Defensive")) {
                rg_GameMode.check(R.id.Defensive);
            }
            if (mode.equals("Normal")) {
                rg_GameMode.check(R.id.Normal);
            }
        }


        rg_Difficulty.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadioButtonDifficulty = (RadioButton) findViewById(checkedId);
                pref = getApplicationContext().getSharedPreferences(PREF, 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(DIFFICULTY_STATE, (String) selectedRadioButtonDifficulty.getText());
                editor.apply();
                Toast.makeText(Setting.this, "clicked " + selectedRadioButtonDifficulty.getText(), Toast.LENGTH_LONG).show();
            }
        });

        rg_GameMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadioButtonGameMode = (RadioButton) findViewById(checkedId);
                pref = getApplicationContext().getSharedPreferences(PREF, 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(GAME_MODE_STATE, (String) selectedRadioButtonGameMode.getText());
                editor.apply();
                Toast.makeText(Setting.this, "clicked " + selectedRadioButtonGameMode.getText(), Toast.LENGTH_LONG).show();
            }
        });

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}