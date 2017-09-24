package com.moldedbits.languagetools.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ArticleSummary(val category: TranslatableString = TranslatableString(),
                          val title: TranslatableString = TranslatableString(),
                          val publishedDate: String = "")
