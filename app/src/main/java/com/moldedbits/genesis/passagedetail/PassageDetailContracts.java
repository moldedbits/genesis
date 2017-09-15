package com.moldedbits.genesis.passagedetail;


import com.moldedbits.genesis.models.response.PassageDetails;

public class PassageDetailContracts {

    interface PassageViewContracts {
        void setupView();

        void populateData(PassageDetails details);
    }

    interface Presenter {

        void init();
    }
}
