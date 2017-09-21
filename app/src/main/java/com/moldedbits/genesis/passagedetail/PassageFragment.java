package com.moldedbits.genesis.passagedetail;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.moldedbits.genesis.BaseFragment;
import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.response.PassageDetails;
import com.moldedbits.genesis.utils.Utilities;
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

    PopupWindow popupWindow;

    int popupPadding;

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

        popupPadding = (int) Utilities.convertDpToPx(20, getResources());
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
    public void onClick(@NonNull String original, @NotNull String translation, int x, int y) {
        // tvPassage.highlight(original);
        // TranslationDialog.newInstance(original, translation).show(getFragmentManager(), "Translation");

        View rootView;
        if (popupWindow == null) {
            rootView = LayoutInflater.from(getContext())
                    .inflate(R.layout.popup_window, null);
            popupWindow = new PopupWindow(rootView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            // Close window on outside click
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupWindow.setOutsideTouchable(true);

            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    tvPassage.removeHighlight();
                }
            });
        } else {
            rootView = popupWindow.getContentView();
        }

        ((TextView) rootView.findViewById(R.id.text_translation)).setText(translation);
        popupWindow.showAsDropDown(tvPassage, x, y + popupPadding);
    }
}
