package com.githiomi.renu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.githiomi.renu.R;
import com.githiomi.renu.models.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandingActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnFocusChangeListener{

//    TAG
    private static final String TAG = LandingActivity.class.getSimpleName();

//    Widget
    @BindView(R.id.tvTableTitle) TextView wTableTitle;
    @BindView(R.id.edTableNumber) EditText wTableNumber;
    @BindView(R.id.btnProceedToCategories) Button wProceedToCategories;

//    Local variables
    // For the table number
    private String mTableNumber;
    // Shared preferences
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Log.d(TAG, "onCreate: Landing page init");

//        Butter knife binding
        ButterKnife.bind(this);

//        Setting a typeface to the app name
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Bhellvast (dafont).ttf");
        wTableTitle.setTypeface(typeface);

//        Shared preferences
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferencesEditor = mSharedPreferences.edit();

//        Listeners
        // Making the button inactive
        wProceedToCategories.setClickable(false);
        // Button on click listener
        wProceedToCategories.setOnClickListener(this);
        wTableNumber.setOnFocusChangeListener(this);
    }

//    The override method for when the user clicks the proceed button
    @Override
    public void onClick(View v) {

        if ( v == wProceedToCategories ){
            mTableNumber = wTableNumber.getText().toString().trim();

            if ( !(mTableNumber.equals("")) ){
                int tableIndex = Integer.parseInt(mTableNumber);

                if ( tableIndex < 0 || tableIndex >= 50){
                    wTableNumber.setError("Enter a valid table number!");
                    return;
                }

                // Send user to the category page
                Intent toCategoryPage = new Intent(LandingActivity.this, MainActivity.class);
                mSharedPreferencesEditor.putString(Constants.TABLE_NUMBER, mTableNumber).apply();
                startActivity(toCategoryPage);

            }else {
                wTableNumber.setError("Field cannot be blank!");
                return;
            }
        }
    }

//    The method that will activate the proceed button
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if ( v == wTableNumber ){
            activateButton();
            hideKeyboard(v);
        }
    }

//    Methods for the on focus change listener
    //    Custom method to hide the keyboard
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //  Custom method to activate the button
    public void activateButton(){
        if ( !(wTableNumber.equals("")) ) {
            wProceedToCategories.setClickable(true);
            wProceedToCategories.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            wProceedToCategories.setElevation(30);
        }
    }
}