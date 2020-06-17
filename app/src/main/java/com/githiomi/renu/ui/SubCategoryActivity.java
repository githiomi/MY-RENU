package com.githiomi.renu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.githiomi.renu.R;

public class SubCategoryActivity extends AppCompatActivity {

//    TAG
    private static final String TAG = SubCategoryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

//        To ensure this activity is started
        Log.d(TAG, "onCreate: Sub Category Activity init");
    }
}