package com.githiomi.renu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.githiomi.renu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnFocusChangeListener {

//    TAG
    private static final String TAG = LoginActivity.class.getSimpleName();

//    Binding widgets using butter knife
    @BindView(R.id.edEmail) EditText wUserEmail;
    @BindView(R.id.edPassword) EditText wUserPassword;
    @BindView(R.id.btnLogin) Button wLoginButton;
    @BindView(R.id.tvAppName) TextView mAppName;
    @BindView(R.id.tvToSignUp) TextView mToSignUp;

//    Local variables
    // Date entry
    private String mEmail;
    private String mPassword;
    // Firebase
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.githiomi.renu.R.layout.activity_login);

//        Adding butter knife to this activity
        ButterKnife.bind(this);

//        Setting a typeface
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Bhellvast (dafont).ttf");
        mAppName.setTypeface(typeface);


//        Initializing the firebase variables
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                // If there is a firebase user, load directly to the main activity
                if ( firebaseUser != null ){
                    Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                    loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(loginIntent);
                    finish();
                }
            }
        };

//        Click listeners and focus changers
        // Click listeners
        wLoginButton.setOnClickListener(this);
        mToSignUp.setOnClickListener(this);
        // Focus change
        wUserEmail.setOnFocusChangeListener(this);
        wUserPassword.setOnFocusChangeListener(this);

    }

//    Custom method to login the user
    public void userLogin(){
        mEmail = wUserEmail.getText().toString().trim();
        mPassword = wUserPassword.getText().toString().trim();

        if ( mEmail.equals("") ){
            String errorMessage = "Invalid email";
            wUserEmail.setError(errorMessage);
        }

        if ( mPassword.equals("") ) {
            String errorMessage = "Invalid password";
            wUserPassword.setError(errorMessage);
        }

        mFirebaseAuth.signInWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                // if the user had not yet logged in, allow the to do so
                if ( task.isSuccessful() ) {
                    Toast.makeText(LoginActivity.this, "Signed in", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

//    Custom method that will activate the login button if fields are filled
    private void activateLoginButton() {
        if ( !(wUserEmail.equals("") && !(wUserPassword.equals("")))) {
            wLoginButton.setClickable(true);
            wLoginButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

//    Listeners
    @Override
    public void onClick(View v) {
        if ( v == wLoginButton ){
            userLogin();
        }

        if ( v == mToSignUp ){
            Intent toSignUp = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(toSignUp);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if ( v == wUserEmail ){
            hideKeyboard(v);
        }

        if ( v == wUserPassword ){
            if ( !(wUserEmail.equals("") && !(wUserPassword.equals("")))) {
                activateLoginButton();
            }
            hideKeyboard(v);
        }
    }

//    Custom method to hide the keyboard
    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

//    Class overrides
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