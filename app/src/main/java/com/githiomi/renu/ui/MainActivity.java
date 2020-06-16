package com.githiomi.renu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.githiomi.renu.R;
import com.githiomi.renu.fragments.MainActivityFragment;
import com.githiomi.renu.models.Category;
import com.githiomi.renu.models.Constants;
import com.githiomi.renu.network.RenuApi;
import com.githiomi.renu.network.RenuClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//    TAG
    private static final String TAG = MainActivity.class.getSimpleName();

//    Local variables
    // Shared preferences
    private SharedPreferences mSharedPreference;
    // Variable to store obtained table number
    private String retrievedTableNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: MainActivity init");

//        Initializing the shared preference
        mSharedPreference = PreferenceManager.getDefaultSharedPreferences(this);

//        Getting table number from shared preferences
        retrievedTableNumber = mSharedPreference.getString(Constants.TABLE_NUMBER, null);
        Log.d(TAG, "onCreate: Obtained table number --------------------------------- " + retrievedTableNumber);

    }
}