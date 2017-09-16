package com.moldedbits.genesis.category;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.moldedbits.genesis.BaseActivity;
import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.Category;
import com.moldedbits.genesis.passage.PassageActivity;
import com.moldedbits.genesis.utils.Utilities;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends BaseActivity implements CategoryContract.IView,
        CategoryListAdaptor.CategoryClickListener{

    @BindView(R.id.rvCategory)
    RecyclerView categoryRecyclerView;

    @BindView(R.id.categories_content)
    View contentContainer;

    @BindView(R.id.categories_progress)
    View progressView;

    CategoryPresenter categoryPresenter;

    private CategoryListAdaptor adapter;

    List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        adapter = new CategoryListAdaptor(this, this);
        categoryPresenter = new CategoryPresenter(this);
        categoryPresenter.getCategories();

        AssetManager am = getAssets();
        Typeface typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "timeburnerbold.ttf"));
        ((TextView) findViewById(R.id.app_name)).setTypeface(typeface);
    }

    @Override
    public void showCategories(List<Category> categories) {
        this.categories = categories;
        adapter.setCategoriesList(categories);
        categoryRecyclerView.setAdapter(adapter);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter.notifyDataSetChanged();

        progressView.setVisibility(View.GONE);
        contentContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(View view, int position) {
        if(categories == null || categories.isEmpty()){
            return;
        }
        Intent intent = new Intent(this, PassageActivity.class);
        intent.putExtra(Utilities.PASSAGE_KEY, categories.get(position).getKey());
        startActivity(intent);
    }
}
