package com.moldedbits.genesis.models.response


data class PassageDetails(val difficulty: String = "",
                          val displayName: TranslatableString = TranslatableString(),
                          val passageText: TranslatableString = TranslatableString(),
                          val questions: List<Question> = emptyList(),
                          val sentences: List<TranslatableString> = emptyList()
)

data class TranslatableString(val english: String = "", val spanish: String = "")

data class Question(val answerText: String = "",
                    val questionText: TranslatableString = TranslatableString(),
                    val type: String = "",
                    val options: List<String> = emptyList())