package com.moldedbits.genesis.passagedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.response.PassageDetails;
import com.moldedbits.genesis.models.response.Question;
import com.moldedbits.genesis.models.response.TranslatableString;
import com.moldedbits.genesis.widgets.QuestionView;
import com.moldedbits.genesis.widgets.TranslatableTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PassageQuestionsFragment extends Fragment {

    private PassageDetails passageDetails;

    @BindView(R.id.label_questions)
    TranslatableTextView labelQuestions;

    @BindView(R.id.question_container)
    LinearLayout questionContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passage_questions, container, false);
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
        if (labelQuestions != null) {
            populateData(passageDetails);
        }
    }

    private void populateData(PassageDetails details) {
        // Populate questions
        TranslatableString label = new TranslatableString(
                getString(R.string.questions_english),
                getString(R.string.questions_spanish));
        labelQuestions.setText(label);

        questionContainer.removeAllViews();
        for (int i=0; i<details.getQuestions().size(); i++) {
            Question question = details.getQuestions().get(i);
            QuestionView view = new QuestionView(getContext());
            view.setQuestion(i + 1, question);
            questionContainer.addView(view);
        }
    }
}
