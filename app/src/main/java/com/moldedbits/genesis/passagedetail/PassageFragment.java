package com.moldedbits.genesis.passagedetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moldedbits.genesis.BaseFragment;
import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.response.PassageDetails;
import com.moldedbits.genesis.widgets.TranslatableTextView;
import com.moldedbits.genesis.widgets.TranslationDialog;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PassageFragment extends BaseFragment implements
        TranslatableTextView.TranslatableClickListener {

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

    @Override
    public void onClick(@NonNull String original, @NotNull String translation) {
        TranslationDialog.newInstance(original, translation).show(getFragmentManager(), "Translation");
    }
}
