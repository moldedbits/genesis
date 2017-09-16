package com.moldedbits.genesis.models;

import com.google.firebase.database.IgnoreExtraProperties;
import com.moldedbits.genesis.models.response.TranslatableString;

import lombok.Data;
import lombok.Getter;

@Data
@IgnoreExtraProperties
public class Category {

    String key ;

    TranslatableString name;

}
