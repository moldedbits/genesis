package com.moldedbits.genesis.models;

import com.google.firebase.database.IgnoreExtraProperties;

import lombok.Data;
import lombok.Getter;

@Data
@IgnoreExtraProperties
public class Category {

    String key ;

    String name;

}
