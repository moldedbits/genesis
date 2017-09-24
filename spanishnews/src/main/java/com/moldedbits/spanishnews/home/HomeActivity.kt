package com.moldedbits.spanishnews.home

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.moldedbits.languagetools.models.ArticleSummary
import com.moldedbits.spanishnews.R
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity(), HomeView {

    private val adapter: ArticleAdapter = ArticleAdapter(this)
    private val presenter: HomePresenter = HomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()

        presenter.fetchArticles()
    }

    private fun initUi() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter
    }

    override fun populateArticles(articles: List<ArticleSummary>) {
        adapter.clear()
        adapter.addAll(articles)
        adapter.notifyDataSetChanged()
    }

    class ArticleAdapter(private val context: Context) :
            RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

        private val items: MutableList<ArticleSummary> = mutableListOf()

        fun clear() {
            items.clear()
        }

        fun addAll(articles: List<ArticleSummary>) {
            items.addAll(articles)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):
                ArticleViewHolder {
            val view = LayoutInflater.from(context)
                    .inflate(R.layout.list_item_article, parent, false)
            return ArticleViewHolder(view)
        }

        override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
            holder.originalView.text = items[position].title.spanish
        }

        class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val originalView: TextView = itemView.findViewById(R.id.title_original)
        }
    }
}