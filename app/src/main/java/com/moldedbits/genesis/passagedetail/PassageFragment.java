package com.moldedbits.genesis.passagedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.moldedbits.genesis.BaseFragment;
import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.response.PassageDetails;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.moldedbits.genesis.passagedetail.PassageDetailContracts.PassageViewContracts;

public class PassageFragment extends BaseFragment implements PassageViewContracts {

    @BindView(R.id.tv_passage)
    TextView tvPassage;
    @BindView(R.id.ll_parent)
    LinearLayout llParent;
    private PassageDetailPresenter passageDetailPresenter;

    @Override
    public void setupView() {

    }

    @Override
    public void populateData(PassageDetails details) {
        String passageText = details.getPassageText().getSpanish();

        SpannableStringBuilder text = new SpannableStringBuilder();
        text.append(details.getPassageText().getSpanish());

        for (final PassageDetails.Sentence sentence : details.getSentences()) {
            int startIndex = passageText.indexOf(sentence.getSpanish());
            int endIndex = startIndex + sentence.getSpanish().length();

            final ClickableSpan span = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Toast.makeText(getActivity(), sentence.getEnglish(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            };

            text.setSpan(span, startIndex, endIndex,
                    SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        tvPassage.setText(text);
        tvPassage.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passage, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        passageDetailPresenter = new PassageDetailPresenter(this);
    }
}
