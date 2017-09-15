package com.moldedbits.genesis.passagedetail;


import com.moldedbits.genesis.BaseApplication;

public class PassageDetailPresenter implements PassageDetailContracts.Presenter, DatabaseCallbacks {


    private final PassageDetailContracts.PassageViewContracts passageView;

    PassageDetailPresenter(PassageDetailContracts.PassageViewContracts passageView) {
        this.passageView = passageView;
        init();
    }

    @Override
    public void init() {
        BaseApplication.getFirebaseInteractor().setCallBacks(this);
        BaseApplication.getFirebaseInteractor().getDataCategories("categories");
    }

    @Override
    public void onDataCategoriesFetched(Object object) {
    }
}
