package com.moldedbits.genesis.passagedetail;

import com.moldedbits.languagetools.models.response.PassageDetails;

public class PassageDetailContracts {

    interface PassageViewContracts {
        void showContent();

        void populateData(PassageDetails details);
    }
}
