package com.githiomi.renu.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.githiomi.renu.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DessertOptionsAdapter extends RecyclerView.Adapter<DessertOptionsAdapter.DessertViewHolder> {

//    TAG
    private static final String TAG = DessertOptionsAdapter.class.getSimpleName();

//    Local variables
    // For incoming data
    private String[] mDessertNames;
    private int[] mDessertImages;
    // context
    private Context mContext;


//    Adapter constructor
    public DessertOptionsAdapter(String[] dessertNames, int[] dessertImages) {
        this.mDessertNames = dessertNames;
        this.mDessertImages = dessertImages;
    }

    @NonNull
    @Override
    public DessertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Inflate the view holder with the layout for a single item
        Log.d(TAG, "onCreateViewHolder: Created the view holder");

        View itemViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.dessert_options_item, parent, false);
        DessertViewHolder dessertViewHolder = new DessertViewHolder(itemViewHolder);
        return dessertViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DessertViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Bound the view holder and passed items");

        holder.bindDessertItems(mDessertNames[position], mDessertImages[position]);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DessertViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        Picasso resizing
        private final int MAX_WIDTH = 300;
        private final int MAX_HEIGHT = 300;

//        binding widgets
        @BindView(R.id.tvDessertOptionName) TextView dessertOptionName;
        @BindView(R.id.dessertItemBackgroundImage) ImageView dessertOptionImage;

        public DessertViewHolder(@NonNull View itemView) {
            super(itemView);

//            Binding views using butter knife
            ButterKnife.bind(this, itemView);

//            Getting the context
            mContext = itemView.getContext();

//            Setting the on click listener
            itemView.setOnClickListener(this);
        }

//        Custom method that will bind the items passed to the views
        public void bindDessertItems(String name, int imageId){

            Picasso.get().load(imageId)
                            .resize(MAX_WIDTH, MAX_HEIGHT)
                            .centerCrop()
                            .into(dessertOptionImage);

            dessertOptionName.setText(name);

        }

        @Override
        public void onClick(View v) {

            if ( v == itemView ){
                Toast.makeText(mContext, "Clicked!", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
