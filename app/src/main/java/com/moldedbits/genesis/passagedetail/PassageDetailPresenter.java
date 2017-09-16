package com.moldedbits.genesis.passagedetail;

import com.moldedbits.genesis.BaseApplication;
import com.moldedbits.genesis.models.response.PassageDetails;
import com.moldedbits.genesis.utils.Utilities;

public class PassageDetailPresenter implements DatabaseCallbacks {

    private final PassageDetailContracts.PassageViewContracts passageView;

    PassageDetailPresenter(PassageDetailContracts.PassageViewContracts passageView) {
        this.passageView = passageView;
    }

    void init(String categoryKey, int passageIndex) {
        BaseApplication.getFirebaseInteractor().setCallBacks(this);
        BaseApplication.getFirebaseInteractor()
                .getDataPassageDetails(Utilities.FB_PASSAGE_DETAILS, categoryKey, passageIndex);
    }

    @Override
    public void onDataPassagesFetched(PassageDetails passageDetails) {
        passageView.populateData(passageDetails);
        passageView.showContent();
    }
}
