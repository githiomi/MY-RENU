package com.githiomi.renu.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.githiomi.renu.R;
import com.githiomi.renu.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InitialActivityFragment extends Fragment {

//    TAG
    private static final String TAG = InitialActivityFragment.class.getSimpleName();

//    Binding widgets from the fragments
    @BindView(R.id.initialPageProgressBar) ProgressBar mProgressBar;

//    Local variables to be used
    private Context mContext;

    public InitialActivityFragment() {
        // Required empty public constructor
    }

    public static InitialActivityFragment newInstance(String param1, String param2) {
        InitialActivityFragment fragment = new InitialActivityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_initial_activity, container, false);

//        Setting this fragments context
        mContext = getContext();

//        Using butter knife to bind views
        ButterKnife.bind(this, mainView);

//        Method to load the progress bar
        loadBeforeProceed();

        return mainView;
    }

//    This is the method that will run the progress bar for a while before going to main activity
    private void loadBeforeProceed() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToIntent();
            }
        }, 2000);
    }

//    Custom method that will take the user from this initial loading page to the login activity
    private void goToIntent() {

        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);

    }

}