package com.githiomi.renu.adapters;

import android.content.Context;
import android.content.Intent;
import android.icu.util.ULocale;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.githiomi.renu.R;
import com.githiomi.renu.models.Category;
import com.githiomi.renu.ui.SubCategoryActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

//    Local variables for the adapter
    // for the list of categories
    private List<Category> mCategoriesList;
    // For the context
    private Context mContext;


    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.mContext = context;
        this.mCategoriesList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bindCategory(mCategoriesList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategoriesList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        Binding widgets from the single item layout file
        @BindView(R.id.ivCategoryImage) ImageView mCategoryImageView;
        @BindView(R.id.tvCategoryName) TextView mCategoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

//            Binding the view using butter knife
            ButterKnife.bind(this, itemView);

//            Setting the context
            mContext = itemView.getContext();

//            Giving the entire item an onclick listener
            itemView.setOnClickListener(this);

        }

        public void bindCategory(Category category){

            final int MAX_WIDTH = 200;
            final int MAW_HEIGHT = 200;

            Picasso.get()
                    .load(category.getImageUrl())
                    .resize(MAX_WIDTH, MAW_HEIGHT)
                    .centerCrop()
                    .into(mCategoryImageView);

            mCategoryName.setText(category.getName());
        }

//        Overridden method for the item on click listener
        @Override
        public void onClick(View v) {

            Toast.makeText(mContext, "Clicked: " + mCategoriesList.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();

            Intent toDessertActivity = new Intent(mContext, SubCategoryActivity.class);
            mContext.startActivity(toDessertActivity);

        }
    }
}
