package com.moldedbits.languagetools.models.response

import com.moldedbits.languagetools.models.TranslatableString


data class PassageDetails(val difficulty: String = "",
                          val displayName: TranslatableString = TranslatableString(),
                          val passageText: TranslatableString = TranslatableString(),
                          val questions: List<Question> = emptyList(),
                          val sentences: List<TranslatableString> = emptyList()
)

data class Question(val answerText: String = "",
                    val questionText: TranslatableString = TranslatableString(),
                    val type: String = "",
                    val options: List<String> = emptyList())