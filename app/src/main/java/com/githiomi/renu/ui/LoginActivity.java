package com.githiomi.renu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.githiomi.renu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

//    TAG
    private static final String TAG = LoginActivity.class.getSimpleName();

//    Binding widgets using butter knife
    @BindView(R.id.edEmail) EditText wUserEmail;
    @BindView(R.id.edPassword) EditText wUserPassword;
    @BindView(R.id.btnLogin) Button wLoginButton;

//    Local variables
    // Date entry
    private String mEmail;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.githiomi.renu.R.layout.activity_login);

//        Adding butter knife to this activity
        ButterKnife.bind(this);


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

    }

}