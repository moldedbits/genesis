package com.moldedbits.genesis.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.moldedbits.genesis.BaseActivity;
import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.Category;
import com.moldedbits.genesis.models.Passage;
import com.moldedbits.genesis.passage.PassageActivity;
import com.moldedbits.genesis.utils.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MOLDEDBITS on 15-09-2017.
 */

public class CategoryActivity extends BaseActivity implements CategoryContract.IView,
        CategoryListAdaptor.CategoryClickListener{

    CategoryPresenter categoryPresenter ;
    private CategoryListAdaptor adapter;
    @BindView(R.id.rvCategory)
    RecyclerView categoryRecyclerView;

    List<Category> categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        adapter = new CategoryListAdaptor(this);
        categoryPresenter = new CategoryPresenter(this);
        categoryPresenter.getCategories();
    }

    @Override
    public void showCategories(List<Category> categories) {
        this.categories = categories;
        adapter.setCategoriesList(categories);
        categoryRecyclerView.setAdapter(adapter);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter.notifyDataSetChanged();
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
