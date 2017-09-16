package com.moldedbits.genesis.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moldedbits.genesis.R;

public class TranslationDialog extends DialogFragment {

    private static final String KEY_ORIGINAL = "original";
    private static final String KEY_TRANSLATION = "translation";

    private String original, translation;

    public static TranslationDialog newInstance(String original, String translation) {
        TranslationDialog d = new TranslationDialog();
        Bundle args = new Bundle();
        args.putString(KEY_ORIGINAL, original);
        args.putString(KEY_TRANSLATION, translation);
        d.setArguments(args);
        return d;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        original = getArguments().getString(KEY_ORIGINAL);
        translation = getArguments().getString(KEY_TRANSLATION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_translation_dialog, container, false);
        ((TextView) v.findViewById(R.id.text_original)).setText(original);
        ((TextView) v.findViewById(R.id.text_translation)).setText(translation);
        v.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslationDialog.this.dismiss();
            }
        });
        return v;

    }
}
