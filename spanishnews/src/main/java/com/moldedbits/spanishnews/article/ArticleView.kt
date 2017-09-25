package com.moldedbits.spanishnews.article

import com.moldedbits.languagetools.models.ArticleSummary

interface ArticleView {

    fun populateArticle(article: ArticleSummary)
}