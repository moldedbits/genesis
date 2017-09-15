package com.moldedbits.genesis;

import com.moldedbits.genesis.api.MockApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MockApiModule.class})
interface MockApiComponent extends ApiComponent {
}
