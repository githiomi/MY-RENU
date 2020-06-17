package com.githiomi.renu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.githiomi.renu.R;
import com.githiomi.renu.adapters.CakeDessertAdapter;
import com.githiomi.renu.models.Desserts;
import com.githiomi.renu.network.RenuApi;
import com.githiomi.renu.network.RenuClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dessert_CakeActivity extends AppCompatActivity implements View.OnClickListener{

//    TAG
    private static final String TAG = Dessert_CakeActivity.class.getSimpleName();

//    Binding widgets using butter knife
    @BindView(R.id.cakeRecyclerView) RecyclerView wCakeRecyclerView;
    @BindView(R.id.pageTitleDessert) TextView wBackToDessertCategory;
    @BindView(R.id.tvErrorMessage) TextView wErrorMessage;
    @BindView(R.id.prLoadingCategories) ProgressBar mProgressBar;

//    Local variables
    // For the list of call backs
    private List<Desserts> mDessertsList;
    // For the cake adapter
    private CakeDessertAdapter cakeDessertAdapter;

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

//        Show loading of the progress bar
        showLoading();

//        Method that will make the API call
        getCakesFromApi();

    }

//    This is the method that will get cakes from the database
    private void getCakesFromApi() {

        final RenuApi renuApi = RenuClient.getRenuApi();

        Call< List<Desserts> > getCakesFromApiCall = renuApi.getCakesInDessert();

        getCakesFromApiCall.enqueue(new Callback<List<Desserts>>() {
            @Override
            public void onResponse(Call<List<Desserts>> call, Response<List<Desserts>> response) {
                Log.d(TAG, "onResponse: ------------------------- Able to get response");

                if ( response.isSuccessful() )
                    Log.d(TAG, "onResponse: Call was a success and got data");
                    showIsSuccess();

                    mDessertsList = new ArrayList<>();

                    mDessertsList = response.body();

//                    Method that will pass all the cakes to my adapter
                    passResponseToAdapter(mDessertsList);

            }

            @Override
            public void onFailure(Call<List<Desserts>> call, Throwable t) {
                Log.d(TAG, "onFailure: ========================== Database call failure");
                showIsFailure();
            }
        });
    }

//    Method implementation that passes the list of cakes to the adapter
    private void passResponseToAdapter(List<Desserts> desserts){

        cakeDessertAdapter = new CakeDessertAdapter(Dessert_CakeActivity.this, mDessertsList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Dessert_CakeActivity.this, 2, GridLayoutManager.VERTICAL, false);

        wCakeRecyclerView.setAdapter(cakeDessertAdapter);
        wCakeRecyclerView.setLayoutManager(gridLayoutManager);
        wCakeRecyclerView.setHasFixedSize(true);

        cakeDessertAdapter.notifyDataSetChanged();

    }

//    These are the methods that are used to control the layout depending on the availability of output
    private void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void showIsSuccess() {
        mProgressBar.setVisibility(View.GONE);
        wCakeRecyclerView.setVisibility(View.VISIBLE);
        Log.d(TAG, "showIsSuccess: output was retrieved");
    }

    private void showIsFailure() {
        mProgressBar.setVisibility(View.GONE);
        wErrorMessage.setVisibility(View.VISIBLE);
    }


//    Method that will take me back to the dessert options activity
    @Override
    public void onClick(View v) {

        Intent backToDessertOptions = new Intent(this, SubCategoryActivity.class);
        startActivity(backToDessertOptions);

    }
}