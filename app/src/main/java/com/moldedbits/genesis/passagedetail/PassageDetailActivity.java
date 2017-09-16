package com.moldedbits.genesis.passagedetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.moldedbits.genesis.BaseActivity;
import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.response.PassageDetails;
import com.moldedbits.genesis.utils.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PassageDetailActivity extends BaseActivity implements PassageDetailContracts.PassageViewContracts {

    @BindView(R.id.view_pager)
    ViewPager pager;

    @BindView(R.id.progress)
    View progressView;

    private String categoryKey;
    private int passageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passage_detail);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            categoryKey = extras.getString(Utilities.CATEGORY_KEY);
            passageIndex = extras.getInt(Utilities.PASSAGE_INDEX);

            PassageDetailPresenter presenter = new PassageDetailPresenter(this);
            presenter.init(categoryKey, passageIndex);
        }
    }

    @Override
    public void showContent() {
        pager.setVisibility(View.VISIBLE);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void populateData(PassageDetails details) {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), details);
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(adapter);
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        private PassageDetails passageDetails;

        PagerAdapter(FragmentManager fm, PassageDetails details) {
            super(fm);
            this.passageDetails = details;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                PassageFragment f = new PassageFragment();
                f.setPassage(passageDetails);
                return f;
            } else {
                PassageQuestionsFragment f = new PassageQuestionsFragment();
                f.setPassage(categoryKey, passageIndex, passageDetails);
                return f;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Passage";
            }
            return "Questions";
        }
    }
}
