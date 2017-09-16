package com.moldedbits.genesis.passage;

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
import com.moldedbits.genesis.models.Passage;
import com.moldedbits.genesis.passagedetail.PassageDetailActivity;
import com.moldedbits.genesis.utils.Utilities;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PassageActivity extends BaseActivity implements PassageContract.IView,
        PassageListAdaptor.PassageSelectedListener {

    @BindView(R.id.rvPassage)
    RecyclerView passageRecyclerView;

    @BindView(R.id.content)
    View contentContainer;

    @BindView(R.id.progress)
    View progressView;

    @BindView(R.id.title)
    TextView titleView;

    PassagePresenter passagePresenter;

    private PassageListAdaptor adapter;

    private String passageKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passage);
        ButterKnife.bind(this);
        adapter = new PassageListAdaptor(this, this);
        passagePresenter = new PassagePresenter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            passageKey = extras.getString(Utilities.CATEGORY_KEY);
            passagePresenter.getPassages(passageKey);

            String passageName = extras.getString(Utilities.CATEGORY_NAME);
            titleView.setText(passageName);
            AssetManager am = getAssets();
            Typeface typeface = Typeface.createFromAsset(am,
                    String.format(Locale.US, "fonts/%s", "timeburnerbold.ttf"));
            titleView.setTypeface(typeface);
        }
    }

    @Override
    public void showPassages(List<Passage> passages) {
        adapter.setPassageList(passages);
        passageRecyclerView.setAdapter(adapter);
        passageRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter.notifyDataSetChanged();

        progressView.setVisibility(View.GONE);
        contentContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPassageSelected(int passageIndex) {
        Intent intent = new Intent(this, PassageDetailActivity.class);
        intent.putExtra(Utilities.CATEGORY_KEY, passageKey);
        intent.putExtra(Utilities.PASSAGE_INDEX, passageIndex);
        startActivity(intent);
    }
}
