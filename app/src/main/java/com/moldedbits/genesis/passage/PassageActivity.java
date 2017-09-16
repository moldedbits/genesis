package com.moldedbits.genesis.passage;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.moldedbits.genesis.BaseActivity;
import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.Passage;
import com.moldedbits.genesis.utils.Utilities;

import java.util.List;

import butterknife.BindView;

/**
 * Created by MOLDEDBITS on 15-09-2017.
 */

public class PassageActivity extends BaseActivity implements PassageContract.IView,
        PassageListAdaptor.PassageClickListener{

    PassagePresenter passagePresenter;
    private PassageListAdaptor adapter;
    @BindView(R.id.rvPassage)
    RecyclerView passageRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passage);
        passagePresenter = new PassagePresenter(this);
        passagePresenter.getPassages(getIntent().getExtras().getString(Utilities.PASSAGE_KEY));
    }

    @Override
    public void showPassages(List<Passage> passages) {
        adapter.setCategoriesList(passages);
        passageRecyclerView.setAdapter(adapter);
        passageRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
