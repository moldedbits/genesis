package com.moldedbits.genesis.passagedetail;

import android.os.Bundle;

import com.moldedbits.genesis.BaseActivity;
import com.moldedbits.genesis.R;
import com.moldedbits.genesis.utils.Utilities;

public class PassageDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passage_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String categoryKey = extras.getString(Utilities.CATEGORY_KEY);
            int passageIndex = extras.getInt(Utilities.PASSAGE_INDEX);

            PassageFragment fragment = (PassageFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.passage_fragment);
            fragment.setPassage(categoryKey, passageIndex);
        }
    }
}
