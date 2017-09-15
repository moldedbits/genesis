package com.moldedbits.genesis.passagedetail;


public interface FirebaseInteractor {
    void getDataCategories(String categories);

    void setCallBacks(DatabaseCallbacks callBacks);
}
