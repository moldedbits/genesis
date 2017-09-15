package com.moldedbits.genesis.models;

import com.google.firebase.database.IgnoreExtraProperties;

import lombok.Getter;

@IgnoreExtraProperties
public class Category {

    @Getter
    String name;
}
