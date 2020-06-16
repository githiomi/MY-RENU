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
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity
                            implements View.OnClickListener, View.OnFocusChangeListener{

//    TAG
    private static final String TAG = SignUpActivity.class.getSimpleName();

//    Binding widgets using Butter knife
    @BindView(R.id.tvAppName) TextView mAppName;
    @BindView(R.id.edUsername) EditText wUsername;
    @BindView(R.id.edEmail) EditText wEmail;
    @BindView(R.id.edPassword) EditText wPassword;
    @BindView(R.id.edConfirmPassword) EditText wConfirmPassword;
    @BindView(R.id.btnSignUp) Button wBtnSignUp;
    @BindView(R.id.tvBackToLogin) TextView wBackToLogin;

//    Local variables
    // the username
    private String username;
    // firebase variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        Binding views using butter knife
        ButterKnife.bind(this);

//        Firebase variables authentication
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser signedUpUser = firebaseAuth.getCurrentUser();

                // Method that will take the user to main activity after signing up
                if ( signedUpUser != null ){

                    Intent toTableNumberActivity = new Intent(SignUpActivity.this, MainActivity.class);
                    toTableNumberActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
                    startActivity(toTableNumberActivity);
                    finish();

                }

            }
        };

//        Adding a typeface to the app name
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Bhellvast (dafont).ttf");
        mAppName.setTypeface(typeface);

//        Listeners
        // On click listeners
        wBtnSignUp.setOnClickListener(this);
        wBackToLogin.setOnClickListener(this);
        // focus change listeners
        wUsername.setOnFocusChangeListener(this);
        wEmail.setOnFocusChangeListener(this);
        wPassword.setOnFocusChangeListener(this);
        wConfirmPassword.setOnFocusChangeListener(this);
    }

//    This is the main method that will take user details and use it to create a new account
    public void signUp() {

        // Getting data from widgets
        username = wUsername.getText().toString().trim();
        String email = wEmail.getText().toString().trim();
        String password = wPassword.getText().toString().trim();
        String confirmPassword = wConfirmPassword.getText().toString().trim();

        // Passing the data to be validated
        boolean isValidUsername = isValidName(username);
        boolean isValidEmail = isValidEmail(email);
        boolean isPasswordsValid = isValidPassword(password, confirmPassword);

        if ( !(isValidUsername) || !(isValidEmail) || !(isPasswordsValid) ) return;

        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if ( task.isSuccessful() ){
                    Toast.makeText(SignUpActivity.this, "Signed up!", Toast.LENGTH_SHORT).show();

                    // If is successful, we add the username to the new account
                    FirebaseUser firebaseUser = task.getResult().getUser();
                    addNameToAccount(firebaseUser);

                }else {
                    Toast.makeText(SignUpActivity.this, "Failed to sign up!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    Custom method that will take the input username and add it to firebase
    public void addNameToAccount(final FirebaseUser firebaseUser){

        UserProfileChangeRequest addUsername = new UserProfileChangeRequest.Builder()
                                                    .setDisplayName(username)
                                                    .build();

        firebaseUser.updateProfile(addUsername)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Log.d(TAG, "onComplete: Added the username to the user account -------------------------------");
                                                    }
                                                });
    }

//    Methods to validate user input for sign up
    private boolean isValidName(String name) {
        if (name.equals("")) {
            wUsername.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            wEmail.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            wPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            wConfirmPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }

//    Listeners override methods
    @Override
    public void onClick(View v) {
        if ( v == wBtnSignUp ){
            signUp();
        }

        if ( v == wBackToLogin ){
            Intent backToLogin = new Intent(SignUpActivity.this, LoginActivity.class);
            backToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(backToLogin);
            finish();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if ( v == wUsername ){
            hideKeyboard(v);
        }

        if ( v == wEmail ){
            hideKeyboard(v);
        }

        if ( v == wPassword ){
            hideKeyboard(v);
        }

        if ( v == wConfirmPassword ){
            hideKeyboard(v);
            activateSignUpButton();
        }
    }

//    THis method will make the sign up button active
    public void activateSignUpButton(){
        wBtnSignUp.setClickable(true);
        wBtnSignUp.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        wBtnSignUp.setElevation(30);
    }

//    Custom method that will hide the keyboard when the user clicks anywhere else
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

//    Overriding the class methods
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }
}