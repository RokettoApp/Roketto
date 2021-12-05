package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.model.Article;
import it.rokettoapp.roketto.repository.ArticleRepository;

public class ArticleViewModel extends AndroidViewModel {

    private final ArticleRepository mArticleRepository;
    private MutableLiveData<List<Article>> mArticleListLiveData;
    private MutableLiveData<List<Article>> mReportListLiveData;
    private MutableLiveData<List<Article>> mBlogPostListLiveData;

    public ArticleViewModel(@NonNull Application application) {

        super(application);
        mArticleRepository = new ArticleRepository(application);
    }

    public MutableLiveData<List<Article>> getArticles() {

        if (mArticleListLiveData == null) fetchArticles();
        return mArticleListLiveData;
    }

    public MutableLiveData<List<Article>> getReports() {

        if (mReportListLiveData == null) fetchReports();
        return mReportListLiveData;
    }

    public MutableLiveData<List<Article>> getBlogPosts() {

        if (mBlogPostListLiveData == null) fetchBlogPosts();
        return mBlogPostListLiveData;
    }

    private void fetchArticles() {

        mArticleListLiveData = mArticleRepository.getArticleList();
    }

    private void fetchReports() {

        mReportListLiveData = mArticleRepository.getReportList();
    }

    private void fetchBlogPosts() {

        mBlogPostListLiveData = mArticleRepository.getBlogPostList();
    }

    public void refreshArticles() {

        mArticleRepository.refreshAll();
    }
}
