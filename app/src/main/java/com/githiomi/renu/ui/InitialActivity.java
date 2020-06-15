package com.githiomi.renu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.githiomi.renu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InitialActivity extends AppCompatActivity {

//    Binding the fragment that houses the initial activity layout
    @BindView(R.id.flInitialFragmentView) Fragment mFragmentInitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

//        Adding butter knife that binds the views
        ButterKnife.bind(this);

    }

}