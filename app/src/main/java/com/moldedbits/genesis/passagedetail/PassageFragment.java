package com.moldedbits.genesis.passagedetail;

import android.graphics.Color;
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
import com.moldedbits.genesis.models.response.Question;
import com.moldedbits.genesis.models.response.TranslatableString;
import com.moldedbits.genesis.widgets.QuestionView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.moldedbits.genesis.passagedetail.PassageDetailContracts.PassageViewContracts;

public class PassageFragment extends BaseFragment implements PassageViewContracts {

    @BindView(R.id.passage_title)
    TextView tvTitle;

    @BindView(R.id.tv_passage)
    TextView tvPassage;

    @BindView(R.id.question_container)
    LinearLayout questionContainer;

    @Override
    public void setupView() {

    }

    @Override
    public void populateData(PassageDetails details) {
        String passageText = details.getPassageText().getSpanish();

        tvTitle.setText(details.getDisplayName().getSpanish());

        SpannableStringBuilder text = new SpannableStringBuilder();
        text.append(details.getPassageText().getSpanish());

        for (final TranslatableString sentence : details.getSentences()) {
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
                    ds.setColor(Color.parseColor("#333333"));
                }
            };

            text.setSpan(span, startIndex, endIndex,
                    SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        tvPassage.setText(text);
        tvPassage.setMovementMethod(LinkMovementMethod.getInstance());

        // Populate questions
        questionContainer.removeAllViews();
        for (Question question : details.getQuestions()) {
            QuestionView view = new QuestionView(getContext());
            view.setQuestion(question);
            questionContainer.addView(view);
        }
    }

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
        PassageDetailPresenter passageDetailPresenter = new PassageDetailPresenter(this);
        passageDetailPresenter.init();
    }
}
