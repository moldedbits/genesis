package com.moldedbits.genesis.category;

import android.content.Context;
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

public class CategoryListAdaptor extends RecyclerView.Adapter<CategoryListAdaptor.CategoriesViewHolder> {

    private final Context context;
    private List<Category> categoryList;
    private CategoryClickListener clickListener;

    CategoryListAdaptor(Context context, CategoryClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_category, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, final int position) {
        holder.tvCategoryName.setText(categoryList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    void setCategoriesList(List<Category> categories) {
        this.categoryList = categories;
    }

    public interface CategoryClickListener {
        void onItemClick(View view, int position);
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.title)
        TextView tvCategoryName;

        CategoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
