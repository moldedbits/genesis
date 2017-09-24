package com.moldedbits.spanishnews.home

import com.moldedbits.languagetools.models.ArticleSummary

interface HomeView {

    fun populateArticles(articles: List<ArticleSummary>)
}