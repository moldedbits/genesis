package com.moldedbits.genesis.passagedetail;


import com.moldedbits.languagetools.models.response.PassageDetails;

public interface DatabaseCallbacks {

    void onDataPassagesFetched(PassageDetails passageDetails);
}
