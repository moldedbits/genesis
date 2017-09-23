package com.moldedbits.languagetools.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Category(var key: String = "",
                    var name: TranslatableString = TranslatableString())
