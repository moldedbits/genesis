package com.moldedbits.genesis.passagedetail;


import com.moldedbits.genesis.models.response.PassageDetails;

public interface DatabaseCallbacks {

    void onDataPassagesFetched(PassageDetails passageDetails);
}
