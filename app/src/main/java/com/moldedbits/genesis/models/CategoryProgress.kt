package com.moldedbits.genesis.models

data class CategoryProgress(var categoryKey: String = "",
                            var completedPassages: List<Boolean> = emptyList())
