package com.moldedbits.genesis.passagedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moldedbits.genesis.BaseFragment;
import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.response.PassageDetails;
import com.moldedbits.genesis.models.response.Question;
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
    TextView tvTitle;

    @BindView(R.id.tv_passage)
    TranslatableTextView tvPassage;

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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PassageDetailPresenter passageDetailPresenter = new PassageDetailPresenter(this);
        passageDetailPresenter.init();
    }

    @Override
    public void showContent() {
        progressView.setVisibility(View.GONE);
        contentContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void populateData(PassageDetails details) {
        // Title
        tvTitle.setText(details.getDisplayName().getSpanish());

        // Passage
        tvPassage.setText(details.getPassageText().getSpanish(), details.getSentences());

        // Populate questions
        questionContainer.removeAllViews();
        for (Question question : details.getQuestions()) {
            QuestionView view = new QuestionView(getContext());
            view.setQuestion(question);
            questionContainer.addView(view);
        }
    }
}
