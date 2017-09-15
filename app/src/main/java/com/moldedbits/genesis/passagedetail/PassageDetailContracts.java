package com.moldedbits.genesis.passagedetail;


public class PassageDetailContracts {

    interface PassageViewContracts {
        void setupView();

        void populateData();
    }

    interface Presenter {

        void init();
    }
}
