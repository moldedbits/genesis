package com.moldedbits.genesis.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.Category;
import com.moldedbits.genesis.utils.LocalStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListAdaptor
        extends RecyclerView.Adapter<CategoryListAdaptor.CategoriesViewHolder>
        implements View.OnClickListener {

    private final Context context;
    private List<Category> categoryList;
    private CategoryClickListener clickListener;

    LocalStorage localStorage = LocalStorage.getInstance();

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
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.categorySpanish.setText(category.getName().getSpanish());
        holder.categoryEnglish.setText(category.getName().getEnglish());

        String status = localStorage.getCategoryProgressString(category.getKey());
        holder.status.setText(status);

        holder.statusIcon.setVisibility(status.equalsIgnoreCase("Completed") ?
                View.VISIBLE : View.GONE);

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clickListener.onItemClick(v, (Integer) v.getTag());
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

        @BindView(R.id.title_original)
        TextView categorySpanish;

        @BindView(R.id.title_translation)
        TextView categoryEnglish;

        @BindView(R.id.status)
        TextView status;

        @BindView(R.id.status_icon)
        View statusIcon;

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
