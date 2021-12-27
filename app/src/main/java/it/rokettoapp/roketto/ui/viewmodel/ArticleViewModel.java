package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;
import android.util.Log;

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

    private int currentResults;

    private boolean isLoading;

    public ArticleViewModel(@NonNull Application application) {

        super(application);
        mArticleRepository = new ArticleRepository(application);
        mArticleListLiveData = mArticleRepository.getArticleLiveData();
        mReportListLiveData = mArticleRepository.getReportLiveData();
        mBlogPostListLiveData = mArticleRepository.getBlogPostLiveData();
    }

    public void addNull(){
        List<Article> currentArticleList = mArticleListLiveData.getValue();
        currentArticleList.add(null);
        mArticleListLiveData.setValue(currentArticleList);
    }

    public MutableLiveData<List<Article>> getArticleLiveData() {
        return mArticleListLiveData;
    }

    public MutableLiveData<List<Article>> getReportLiveData() {

        return mReportListLiveData;
    }

    public MutableLiveData<List<Article>> getBlogPostLiveData() {

        return mBlogPostListLiveData;
    }

    public void getArticles() {
        fetchArticles();
    }

    public void getNewArticles() {
        fetchNewArticles();
    }

    public void getReports() {

        fetchReports();
    }

    public void getBlogPosts() {

        fetchBlogPosts();
    }

    private void fetchArticles() {
        mArticleRepository.getArticleList();
    }

    private void fetchNewArticles() {
        mArticleRepository.getNewArticleList();
    }


    private void fetchReports() {
        mArticleRepository.getReportList();
    }

    private void fetchBlogPosts() {

        mArticleRepository.getBlogPostList();
    }

    public void refreshArticles() {

        mArticleRepository.refreshAll();
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public int getCurrentResults() {
        return currentResults;
    }

    public void setCurrentResults(int currentResults) {
        this.currentResults = currentResults;
    }

}
