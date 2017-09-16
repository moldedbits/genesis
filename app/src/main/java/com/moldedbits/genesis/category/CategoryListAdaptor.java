package com.moldedbits.genesis.category;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MOLDEDBITS on 15-09-2017.
 */

public class CategoryListAdaptor extends RecyclerView.Adapter<CategoryListAdaptor.CategoriesViewHolder> {

    private final Context context;
    private List<Category> categoryList;
    private CategoryClickListener clickListener;

    CategoryListAdaptor(Context context) {
        this.context = context;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_category, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        holder.tvCategoryName.setText(categoryList.get(position).getName());
    }

    @Override
    public int getItemCount() {
            return categoryList.size();
    }

    public void setCategoriesList(List<Category> categories) {
        this.categoryList = categories;
    }

    public interface CategoryClickListener {
        void onItemClick(View view, int position);
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.category_name)
        TextView tvCategoryName;
        @BindView(R.id.cvCategory)
        CardView cvCategory;
        @Setter
        @Getter
        private String sku;

        CategoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
