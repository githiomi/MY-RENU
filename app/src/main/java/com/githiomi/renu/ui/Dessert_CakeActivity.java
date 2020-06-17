package com.githiomi.renu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.githiomi.renu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Dessert_CakeActivity extends AppCompatActivity implements View.OnClickListener{

//    TAG
    private static final String TAG = Dessert_CakeActivity.class.getSimpleName();

//    Binding widgets using butter knife
    @BindView(R.id.cakeRecyclerView) RecyclerView wCakeRecyclerView;
    @BindView(R.id.pageTitleDessert) TextView wBackToDessertCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert_cake);

        Log.d(TAG, "onCreate: Dessert CakeActivity init");

//        Binding using butter knife
        ButterKnife.bind(this);

//        Listeners
        // On click to go back to dessert options
        wBackToDessertCategory.setOnClickListener(this);

//        Method that will make the API call
        getCakesFromApi();

    }

//    This is the method that will get cakes from the database
    private void getCakesFromApi() {



    }

    //    Method that will take me back to the dessert options activity
    @Override
    public void onClick(View v) {

        Intent backToDessertOptions = new Intent(this, SubCategoryActivity.class);
        startActivity(backToDessertOptions);

    }
}