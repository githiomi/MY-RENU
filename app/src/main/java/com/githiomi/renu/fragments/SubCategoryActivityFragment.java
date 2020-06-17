package com.githiomi.renu.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.githiomi.renu.R;
import com.githiomi.renu.adapters.DessertOptionsAdapter;
import com.githiomi.renu.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubCategoryActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubCategoryActivityFragment extends Fragment implements View.OnClickListener{

//    TAG
    private static final String TAG = SubCategoryActivityFragment.class.getSimpleName();

//    Hard code data
    // The main names
    private String[] dessertCategories = {"Cakes", "Ice Cream"};
    private String[] dessertDescription = {"Baking the difference. We create delicious memories. Just like home", "We make our own all-natural ice cream using the finest dairy-fresh cream and milk"};
    private int[] dessertImages = {R.drawable.cakesdessert, R.drawable.icecreamdessert};
    // for the adapter
    private DessertOptionsAdapter mDessertOptionsAdapter;

//    Binding widgets
    @BindView(R.id.pageTitleDessert) TextView wTextViewDesserts;
    @BindView(R.id.recycleDesserts) RecyclerView wDessertRecyclerView;

    public SubCategoryActivityFragment() {
        // Required empty public constructor
    }

    public static SubCategoryActivityFragment newInstance() {
        SubCategoryActivityFragment fragment = new SubCategoryActivityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Confirm fragment is initialized
        Log.d(TAG, "onCreate: Sub Category Activity Fragment init");

        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_sub_category_activity, container, false);

//        Binding the widgets using butter knife
        ButterKnife.bind(this, mainView);

//        Setting listeners
        // To go back to categories page
        wTextViewDesserts.setOnClickListener(this);

//        Send data to the adapter to be displayed
        attachItemsToAdapter(dessertCategories, dessertDescription, dessertImages);

        return mainView;
    }

//    Custom method that will create the adapter and pass data to it
    public void attachItemsToAdapter(String[] dessertNames, String[] dessertDescription, int[] dessertImages){
        Log.d(TAG, "attachItemsToAdapter: Attach to adapter init");

        mDessertOptionsAdapter = new DessertOptionsAdapter(getContext(), dessertNames, dessertDescription, dessertImages);

        wDessertRecyclerView.setAdapter(mDessertOptionsAdapter);
        wDessertRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        wDessertRecyclerView.setHasFixedSize(true);

    }

//    On click listener override
    @Override
    public void onClick(View v) {
        if (v == wTextViewDesserts ){
            Intent backToCategories = new Intent(getActivity(), MainActivity.class);
            startActivity(backToCategories);
        }
    }
}