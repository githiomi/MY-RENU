package com.githiomi.renu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.githiomi.renu.R;
import com.githiomi.renu.models.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    // Firebase
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Log.d(TAG, "onCreate: Landing page init");

//        Butter knife binding
        ButterKnife.bind(this);

//        Initializing the firebase variables
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser signedInUser = firebaseAuth.getCurrentUser();

                if ( signedInUser != null ) {
                    String username = signedInUser.getDisplayName();
                    getSupportActionBar().setTitle(username);
                }else{
                    getSupportActionBar().setTitle("Welcome");
                }

            }
        };

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

//    Inflating the menu dropdown
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.landing_page, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.settings ){
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }

        if ( id == R.id.profile ){
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
        }

        if ( id == R.id.logout ){
            // Method to logout of the user account
            Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show();
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

//    Custom method to log out the user
    public void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent backToLogin = new Intent(LandingActivity.this, LoginActivity.class);
        backToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(backToLogin);
        finish();
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

//    Firebase overriding listeners

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if ( mFirebaseAuth != null ){
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }
}