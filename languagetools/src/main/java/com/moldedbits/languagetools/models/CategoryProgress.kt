package com.moldedbits.languagetools.models

data class CategoryProgress(var categoryKey: String = "",
                            var completedPassages: List<Boolean> = emptyList())
