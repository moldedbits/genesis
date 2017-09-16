package com.moldedbits.genesis.passagedetail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.moldedbits.genesis.BaseFragment;
import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.response.PassageDetails;
import com.moldedbits.genesis.widgets.TranslatableTextView;

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

    @BindView(R.id.shadow)
    View shadow;

    private PopupWindow popupWindow;

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

        shadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePopup();
            }
        });
    }

    public void setPassage(PassageDetails passageDetails) {
        this.passageDetails = passageDetails;
        if (tvTitle != null) {
            populateData(passageDetails);
        }
    }

    public void populateData(PassageDetails details) {
        // Title
        tvTitle.setText(details.getDisplayName());

        // Passage
        tvPassage.setTranslatableClickListener(this);
        tvPassage.setText(details.getPassageText().getSpanish(), details.getSentences());
    }

    @Override
    public void onClick(@NonNull String original, @NotNull String translation) {
        View popupContent;
        if (popupWindow == null) {
            popupContent = LayoutInflater.from(getContext())
                    .inflate(R.layout.popup_translation, null);

            popupContent.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hidePopup();
                }
            });

            popupWindow = new PopupWindow(popupContent,
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            popupContent = popupWindow.getContentView();
        }

        ((TextView) popupContent.findViewById(R.id.text_original)).setText(original);
        ((TextView) popupContent.findViewById(R.id.text_translation)).setText(translation);


        showPopup();
    }

    private void showPopup() {
        // Set an elevation value for popup window
        // Call requires API level 21
        if(Build.VERSION.SDK_INT>=21){
            popupWindow.setElevation(5.0f);
        }

        popupWindow.showAtLocation(tvPassage, Gravity.CENTER, 0, 0);
        shadow.setVisibility(View.VISIBLE);
    }

    private void hidePopup() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        shadow.setVisibility(View.GONE);
    }
}
