package com.moldedbits.genesis.passagedetail;

public interface FirebaseInteractor {
    void getDataPassageDetails(String passageDetails, String key, int index);

    void setCallBacks(DatabaseCallbacks callBacks);
}
