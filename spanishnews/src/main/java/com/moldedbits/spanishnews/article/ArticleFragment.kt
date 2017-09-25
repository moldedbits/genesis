package com.moldedbits.spanishnews.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moldedbits.languagetools.models.ArticleSummary
import com.moldedbits.languagetools.widgets.TranslatableTextView
import com.moldedbits.languagetools.widgets.TranslationFragment
import com.moldedbits.spanishnews.R
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : TranslationFragment(), ArticleView {

    private var articleId: Int = 0

    private val presenter: ArticlePresenter = ArticlePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            articleId = arguments.getInt(ARTICLE_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_article, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.fetchArticle(articleId)
    }

    override fun populateArticle(article: ArticleSummary) {
        content.setTranslatableClickListener(this)
        content.setText(article.content.spanish, article.sentences)
    }

    override fun getTranslatableView(): TranslatableTextView {
        return content
    }

    companion object {
        private val ARTICLE_ID = "articleId"

        fun newInstance(articleId: Int): ArticleFragment {
            val fragment = ArticleFragment()
            val args = Bundle()
            args.putInt(ARTICLE_ID, articleId)
            fragment.arguments = args
            return fragment
        }
    }
}