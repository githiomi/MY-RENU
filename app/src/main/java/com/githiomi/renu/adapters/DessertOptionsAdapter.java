package com.githiomi.renu.adapters;

import android.content.Context;
import android.graphics.Typeface;
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
    private String[] mDessertDescriptions;
    private int[] mDessertImages;
    // context
    private Context mContext;
    // for the typeface
    private Typeface typeface;

//    Adapter constructor
    public DessertOptionsAdapter(Context context, String[] dessertNames, String[] dessertDescriptions, int[] dessertImages) {
        this.mContext = context;
        this.mDessertNames = dessertNames;
        this.mDessertDescriptions = dessertDescriptions;
        this.mDessertImages = dessertImages;

        Log.d(TAG, "DessertOptionsAdapter: Get count: " + mDessertNames.length + " " + mDessertImages.length);
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

        holder.bindDessertItems(mDessertNames[position], mDessertDescriptions[position], mDessertImages[position]);
    }

    @Override
    public int getItemCount() {
        return mDessertImages.length;
    }

    public class DessertViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        Picasso resizing
        private final int MAX_WIDTH = 150;
        private final int MAX_HEIGHT = 150;

//        binding widgets
        @BindView(R.id.tvDessertOptionName) TextView dessertOptionName;
        @BindView(R.id.dessertItemBackgroundImage) ImageView dessertOptionImage;
        @BindView(R.id.tvDessertDescription) TextView dessertOptionDescription;

        public DessertViewHolder(@NonNull View itemView) {
            super(itemView);

//            Instantiating the typeface
            typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/Bhellvast (dafont).ttf");

//            Binding views using butter knife
            ButterKnife.bind(this, itemView);

//            Getting the context
//            mContext = itemView.getContext();

//            Setting the on click listener
            itemView.setOnClickListener(this);
        }

//        Custom method that will bind the items passed to the views
        public void bindDessertItems(String name, String description, int imageId){

            Picasso.get().load(imageId)
//                            .resize(MAX_WIDTH, MAX_HEIGHT)
//                            .centerCrop()
                            .into(dessertOptionImage);

            dessertOptionName.setText(name);
            dessertOptionName.setTypeface(typeface);

            dessertOptionDescription.setText(description);

        }

        @Override
        public void onClick(View v) {

            if ( v == itemView ){
                Toast.makeText(mContext, "Clicked!", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
