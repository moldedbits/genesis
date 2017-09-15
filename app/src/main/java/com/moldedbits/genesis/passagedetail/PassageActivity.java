package com.moldedbits.genesis.passagedetail;

import android.os.Bundle;

import com.moldedbits.genesis.BaseActivity;
import com.moldedbits.genesis.R;
import com.moldedbits.genesis.passagedetail.PassageDetailContracts.PassageViewContracts;


public class PassageActivity extends BaseActivity implements PassageViewContracts {

    @Override
    public void setupView() {

    }

    @Override
    public void populateData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passage_detail);
    }
}
