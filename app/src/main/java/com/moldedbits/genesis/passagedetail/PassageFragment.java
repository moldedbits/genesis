package com.moldedbits.genesis.passagedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moldedbits.genesis.R;
import com.moldedbits.languagetools.models.response.PassageDetails;
import com.moldedbits.languagetools.widgets.TranslatableTextView;
import com.moldedbits.languagetools.widgets.TranslationFragment;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PassageFragment extends TranslationFragment {

    private PassageDetails passageDetails;

    @BindView(R.id.passage_title)
    TranslatableTextView tvTitle;

    @BindView(R.id.tv_passage)
    TranslatableTextView tvPassage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passage, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (passageDetails != null) {
            populateData(passageDetails);
        }
    }

    public void setPassage(PassageDetails passageDetails) {
        this.passageDetails = passageDetails;
        if (tvTitle != null) {
            populateData(passageDetails);
        }
    }

    public void populateData(PassageDetails details) {
        // Title
        tvTitle.setTranslatableClickListener(this);
        tvTitle.setText(details.getDisplayName());

        // Passage
        tvPassage.setTranslatableClickListener(this);
        tvPassage.setText(details.getPassageText().getSpanish(), details.getSentences());
    }

    @NotNull
    @Override
    public TranslatableTextView getTranslatableView() {
        return tvPassage;
    }
}
