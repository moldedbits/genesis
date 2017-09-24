package com.moldedbits.spanishnews.home

import com.moldedbits.languagetools.DataProvider
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class HomePresenter(private val homeView: HomeView) {

    fun fetchArticles() {
        DataProvider.getArticles().subscribeBy(
                onSuccess = { homeView.populateArticles(it) },
                onError = { Timber.e(it, "could not fetch articles") },
                onComplete = { Timber.d("onComplete called for getArticles") }
        )
    }
}