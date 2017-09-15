package com.moldedbits.genesis;

import com.moldedbits.genesis.api.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(BaseActivity baseActivity);
}
