package com.moldedbits.genesis;

import android.app.Application;

import com.moldedbits.genesis.passagedetail.FirebaseDataHandler;
import com.moldedbits.genesis.passagedetail.FirebaseInteractor;

import lombok.Getter;
import timber.log.Timber;

public class BaseApplication extends Application {

    private static BaseApplication instance;
    private static FirebaseInteractor firebaseInteractor;

    @Getter
    protected ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        apiComponent = DaggerApiComponent.create();
        firebaseInteractor = new FirebaseDataHandler();
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public static FirebaseInteractor getFirebaseInteractor() {
        return firebaseInteractor;
    }
}
