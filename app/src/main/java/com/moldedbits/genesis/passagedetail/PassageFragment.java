package com.moldedbits.genesis.passagedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.moldedbits.genesis.BaseFragment;
import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.response.PassageDetails;
import com.moldedbits.genesis.models.response.Question;
import com.moldedbits.genesis.models.response.TranslatableString;
import com.moldedbits.genesis.widgets.QuestionView;
import com.moldedbits.genesis.widgets.TranslatableTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.moldedbits.genesis.passagedetail.PassageDetailContracts.PassageViewContracts;

public class PassageFragment extends BaseFragment implements PassageViewContracts {

    @BindView(R.id.passage_content)
    View contentContainer;

    @BindView(R.id.passage_progress)
    View progressView;

    @BindView(R.id.passage_title)
    TranslatableTextView tvTitle;

    @BindView(R.id.tv_passage)
    TranslatableTextView tvPassage;

    @BindView(R.id.label_questions)
    TranslatableTextView labelQuestions;

    @BindView(R.id.question_container)
    LinearLayout questionContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passage, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void setPassage(String categoryKey, int passageIndex) {
        PassageDetailPresenter passageDetailPresenter = new PassageDetailPresenter(this);
        passageDetailPresenter.init(categoryKey, passageIndex);
    }

    @Override
    public void showContent() {
        progressView.setVisibility(View.GONE);
        contentContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void populateData(PassageDetails details) {
        // Title
        tvTitle.setText(details.getDisplayName());

        // Passage
        tvPassage.setText(details.getPassageText().getSpanish(), details.getSentences());

        // Populate questions
        TranslatableString label = new TranslatableString(
                getString(R.string.questions_english),
                getString(R.string.questions_spanish));
        labelQuestions.setText(label);

        questionContainer.removeAllViews();
        for (int i=0; i<details.getQuestions().size(); i++) {
            Question question = details.getQuestions().get(i);
            QuestionView view = new QuestionView(getContext());
            view.setQuestion(i, question);
            questionContainer.addView(view);
        }
    }
}
