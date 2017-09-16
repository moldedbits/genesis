package com.moldedbits.genesis.passagedetail;

import com.moldedbits.genesis.BaseApplication;
import com.moldedbits.genesis.models.response.PassageDetails;

public class PassageDetailPresenter implements PassageDetailContracts.Presenter,
        DatabaseCallbacks {

    private final PassageDetailContracts.PassageViewContracts passageView;

    PassageDetailPresenter(PassageDetailContracts.PassageViewContracts passageView) {
        this.passageView = passageView;
    }

    // TODO keys for passage details and index will be used from previous screen data,
    // for testing purpose it is hardcoded
    @Override
    public void init() {
        BaseApplication.getFirebaseInteractor().setCallBacks(this);
        BaseApplication.getFirebaseInteractor()
                .getDataPassageDetails("passageDetails", "four", 0);
    }

    @Override
    public void onDataPassagesFetched(PassageDetails passageDetails) {
        passageView.populateData(passageDetails);
        passageView.showContent();
    }
}
