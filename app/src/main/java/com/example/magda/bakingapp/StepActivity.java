package com.example.magda.bakingapp;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class StepActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_activity);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("step")) {

            }
            if (intentThatStartedThisActivity.hasExtra("receipe_name")) {
                TextView recepeiName = (TextView) findViewById(R.id.step_receipe_name);
                recepeiName.setText(intentThatStartedThisActivity.getStringExtra("receipe_name"));
            }

        }
    }
}
