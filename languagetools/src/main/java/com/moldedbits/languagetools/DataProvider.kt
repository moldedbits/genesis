package com.moldedbits.languagetools

import com.google.firebase.database.FirebaseDatabase
import com.moldedbits.languagetools.models.ArticleSummary
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Maybe

/**
 * Helper class to access Articles
 */
object DataProvider {

    private val ARTICLES = "articles"

    fun getArticles(): Maybe<List<ArticleSummary>> {
        val firebaseDatabase = FirebaseDatabase.getInstance()
        val articlesRef = firebaseDatabase.getReference(ARTICLES)

        return RxFirebaseDatabase.observeSingleValueEvent(articlesRef,
                DataSnapshotMapper.listOf(ArticleSummary::class.java))
    }

    fun getArticle(articleId: Int) : Maybe<ArticleSummary> {
        val firebaseDatabase = FirebaseDatabase.getInstance()
        val articlesRef = firebaseDatabase.getReference(ARTICLES)
        val articleRef = articlesRef.child(articleId.toString())

        return RxFirebaseDatabase.observeSingleValueEvent(articleRef, ArticleSummary::class.java)
    }
}