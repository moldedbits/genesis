package com.moldedbits.spanishnews.article

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.moldedbits.languagetools.Utilities
import com.moldedbits.spanishnews.R

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val articleId: Int =
                if (intent.extras == null)
                    0
                else
                    intent.extras.getInt(Utilities.ARTICLE_ID, 0)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, ArticleFragment.newInstance(articleId))
                .commit()
    }
}
