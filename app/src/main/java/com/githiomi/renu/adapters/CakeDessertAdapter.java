package com.githiomi.renu.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.githiomi.renu.R;
import com.githiomi.renu.models.Desserts;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CakeDessertAdapter extends RecyclerView.Adapter<CakeDessertAdapter.CakeItemViewHolder> {

//    TAG
    private static final String TAG = CakeDessertAdapter.class.getSimpleName();

//    Local variables
    // Context
    private Context mContext;
    // List of objects
    private List<Desserts> mDesserts;

//    Constructor for the adapter
    public CakeDessertAdapter(Context context, List<Desserts> desserts) {
        Log.d(TAG, "CakeDessertAdapter: Cake Item Adapter init");

        this.mContext = context;
        this.mDesserts = desserts;
    }

    @NonNull
    @Override
    public CakeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Binding the layout of a single cake item to the view holder
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_item, parent, false);
        CakeItemViewHolder cakeItemViewHolder = new CakeItemViewHolder(mainView);
        return cakeItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CakeItemViewHolder holder, int position) {
//        Sending the parcelled object to the bind cake method to be assigned to the views
        holder.bindCake(mDesserts.get(position));

    }

    @Override
    public int getItemCount() {
        return mDesserts.size();
    }

    public class CakeItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        Binding the widgets from a single cake item
        @BindView(R.id.tvCakeName) TextView wCakeName;
        @BindView(R.id.tvCakePrice) TextView wCakePrice;
        @BindView(R.id.btnAddQuantity) Button wAddQuantity;
        @BindView(R.id.btnRemoveQuantity) Button wRemoveQuantity;
        @BindView(R.id.tvQuantity) TextView wQuantity;
        @BindView(R.id.btnAddToCart) Button wAddToOrder;

        public CakeItemViewHolder(@NonNull View itemView) {
            super(itemView);

//            Using butter knife to bind views
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
            wAddToOrder.setOnClickListener(this);
        }

//        Method that will receive a single cake and get its properties and assign them to the views
        public void bindCake(Desserts dessert){

            Log.d(TAG, "bindCake: Bind the cake object init");

            wCakeName.setText(dessert.getName());
            wCakePrice.setText(dessert.getPrice());

//        Getting the quantity of a meal order
            final int orderQuantity = 1;

            wRemoveQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if ( orderQuantity > 1 ) {
                        int newReducedQuantity = (orderQuantity - 1);
                        wQuantity.setText("" + newReducedQuantity);
                    }
                }
            });

            wAddQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int newAddedQuantity = ( orderQuantity + 1 );
                    wQuantity.setText("" + newAddedQuantity);

                }
            });
        }

//        On click listener for each item view
        @Override
        public void onClick(View v) {

            int itemPosition = getAdapterPosition();

            if ( v == itemView ){
                Toast.makeText(mContext, "Clicked!", Toast.LENGTH_SHORT).show();
            }

            if ( v == wAddToOrder ){
                Toast.makeText(mContext, "Ordered " + mDesserts.get(itemPosition).getName(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}
