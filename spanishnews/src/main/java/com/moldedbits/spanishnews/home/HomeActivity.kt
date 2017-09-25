package com.moldedbits.spanishnews.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.moldedbits.languagetools.Utilities
import com.moldedbits.languagetools.models.ArticleSummary
import com.moldedbits.spanishnews.R
import com.moldedbits.spanishnews.article.ArticleActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity(), HomeView,
        ArticleAdapter.ArticleClickListener {

    private val adapter: ArticleAdapter = ArticleAdapter(this, this)
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

    override fun onArticleClick(position: Int) {
        val intent = Intent(this, ArticleActivity::class.java)
        intent.putExtra(Utilities.ARTICLE_ID, position)
        startActivity(intent)
    }
}

class ArticleAdapter(private val context: Context,
                     private val listener: ArticleClickListener) :
        RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private val items: MutableList<ArticleSummary> = mutableListOf()

    interface ArticleClickListener {
        fun onArticleClick(position: Int)
    }

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
        val summary = items[position]
        holder.originalView.text = summary.title.spanish
        holder.categoryView.text = summary.category.spanish
        holder.dateView.text = summary.publishedDate

        holder.itemView.tag = position
        holder.itemView.setOnClickListener(clickListener)
    }

    val clickListener = View.OnClickListener {
        listener.onArticleClick(it.tag as Int)
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val originalView: TextView = itemView.findViewById(R.id.title)
        val categoryView: TextView = itemView.findViewById(R.id.category)
        val dateView: TextView = itemView.findViewById(R.id.published_date)
    }
}