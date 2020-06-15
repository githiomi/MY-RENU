package com.githiomi.renu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.githiomi.renu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InitialActivity extends AppCompatActivity {

//    TAG
    private static final String TAG = InitialActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        Log.d(TAG, "onCreate: Initial activity init");

    }

}