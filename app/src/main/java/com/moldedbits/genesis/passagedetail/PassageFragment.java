package com.moldedbits.genesis.passagedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.FirebaseDatabase;
import com.moldedbits.genesis.BaseFragment;
import com.moldedbits.genesis.R;

import static com.moldedbits.genesis.passagedetail.PassageDetailContracts.PassageViewContracts;


public class PassageFragment extends BaseFragment implements PassageViewContracts {

    private PassageDetailPresenter passageDetailPresenter;

    private FirebaseDatabase firebaseDatabase;

    @Override
    public void setupView() {

    }

    @Override
    public void populateData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passage, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        passageDetailPresenter = new PassageDetailPresenter(this);
    }

}
