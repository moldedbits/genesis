package com.moldedbits.genesis;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface ApiComponent {

    void inject(BaseActivity baseActivity);
}
