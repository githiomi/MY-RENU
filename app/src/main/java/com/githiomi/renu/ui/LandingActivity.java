package com.githiomi.renu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.githiomi.renu.R;

import butterknife.ButterKnife;

public class LandingActivity extends AppCompatActivity {

//    TAG
    private static final String TAG = LandingActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Log.d(TAG, "onCreate: Landing page init");

//        Butter knife binding
        ButterKnife.bind(this);
    }
}