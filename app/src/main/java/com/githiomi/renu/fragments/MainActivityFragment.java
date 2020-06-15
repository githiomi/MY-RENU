package com.githiomi.renu.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.githiomi.renu.R;
import com.githiomi.renu.adapters.CategoryAdapter;
import com.githiomi.renu.models.Category;
import com.githiomi.renu.network.RenuApi;
import com.githiomi.renu.network.RenuClient;
import com.githiomi.renu.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityFragment extends Fragment {

//    TAG
    private static final String TAG = MainActivityFragment.class.getSimpleName();

//    Local variables
    // For context
    private Context mContext = getActivity();
    //for all the categories
    private List<Category> mAllCategories;
    // List of category names
    private List<String> mCategoryNames;
    // for the adapter
    private CategoryAdapter categoryAdapter;

//    Binding widgets
    @BindView(R.id.recyclerCategory) RecyclerView mRecyclerCategoryView;

    public MainActivityFragment() {
        // Required empty public constructor
    }

    public static MainActivityFragment newInstance() {
        MainActivityFragment fragment = new MainActivityFragment();
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
// Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_main_activity, container, false);

//        Butter knife
        ButterKnife.bind(this, mainView);

//        This is the method call that will be used to get data from my API
        getMenuCategories();

        return mainView;
    }


    //    Implementation of the method that will get data from my API
    private void getMenuCategories() {
        Log.d(TAG, "getMenuCategories: method init");

        RenuApi renuApi = RenuClient.getRenuApi();

        Call<List<Category>> call = renuApi.getCategories();

        call.enqueue(new Callback< List<Category> >() {
            @Override
            public void onResponse(Call< List<Category> > call, Response< List<Category> > response) {

                if ( response.isSuccessful() ) {
                    Log.d(TAG, "onResponse: Call was successful and response was gotten");

                    mAllCategories = new ArrayList<>();
                    mCategoryNames = new ArrayList<>();

                    mAllCategories = response.body();

                    for ( int i  = 0; i < mAllCategories.size(); i += 1 ){

                        String categoryName = mAllCategories.get(i).getName();
                        mCategoryNames.add(categoryName);

                    }
                    Log.d(TAG, "onResponse: These are all the category names: " + mCategoryNames);

                    passToAdapter(mAllCategories);
                }
                else {
                    Log.d(TAG, "onResponse: Call was made but responce was not successful");
                }

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d(TAG, "onFailure: Call was not able to be made");

            }
        });

    }

//    Custom method meant to get the data from api to the adapter
    private void passToAdapter(List<Category> categoryList) {

        categoryAdapter = new CategoryAdapter(mContext, categoryList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);

        mRecyclerCategoryView.setAdapter(categoryAdapter);
        mRecyclerCategoryView.setLayoutManager(gridLayoutManager);

        categoryAdapter.notifyDataSetChanged();
    }
}