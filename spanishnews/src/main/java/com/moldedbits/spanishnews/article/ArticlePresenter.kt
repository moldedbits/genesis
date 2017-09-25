package com.moldedbits.spanishnews.article

import com.moldedbits.languagetools.DataProvider
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class ArticlePresenter(val articleView: ArticleView) {

    fun fetchArticle(articleId: Int) {
        DataProvider.getArticle(articleId).subscribeBy (
                onSuccess = { articleView.populateArticle(it) },
                onError = { Timber.e(it, "could not fetch article") },
                onComplete = { Timber.d("onComplete called for fetchArticle") }
        )
    }
}