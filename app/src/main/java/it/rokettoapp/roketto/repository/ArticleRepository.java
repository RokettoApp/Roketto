package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.ArticleDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Article;
import it.rokettoapp.roketto.model.ArticleType;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.ArticleApiService;
import it.rokettoapp.roketto.util.Constants;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import it.rokettoapp.roketto.util.SharedPreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

    private static final String TAG = "ArticleRepository";
    private final ArticleApiService mArticleApiService;
    private final ArticleDao mArticleDao;
    private final DatabaseOperations<Integer, Article> databaseOperations;
    private final MutableLiveData<ResponseList<Article>> mArticleListLiveData;
    private final MutableLiveData<ResponseList<Article>> mReportListLiveData;
    private final MutableLiveData<ResponseList<Article>> mBlogPostListLiveData;
    private final SharedPreferencesProvider mSharedPreferencesProvider;
    int count;

    public ArticleRepository(Application application) {

        this.mArticleApiService = ServiceLocator.getInstance().getArticleApiService();
        mSharedPreferencesProvider = new SharedPreferencesProvider(application);
        mArticleDao = RokettoDatabase.getDatabase(application).articleDao();
        databaseOperations = new DatabaseOperations<>(mArticleDao);
        mArticleListLiveData = new MutableLiveData<>();
        mReportListLiveData = new MutableLiveData<>();
        mBlogPostListLiveData = new MutableLiveData<>();
        count = 0;
    }

    public MutableLiveData<ResponseList<Article>> getArticleLiveData() {

        return mArticleListLiveData;
    }

    public MutableLiveData<ResponseList<Article>> getReportLiveData() {

        return mReportListLiveData;
    }

    public MutableLiveData<ResponseList<Article>> getBlogPostLiveData() {

        return mBlogPostListLiveData;
    }

    public void getArticleList(Boolean isConnected) {

        if(isConnected) {
            refreshAll(true);
            mSharedPreferencesProvider.setLastUpdate(System.currentTimeMillis(), Constants.SHARED_PREFERENCES_LAST_UPDATE_ARTICLE);
        }
        else
            getArticlesFromDatabase();
    }

    public void getNewArticleList () {
        fetchNewArticles();
    }

    public void getReportList(Boolean isConnected) {

        if(mSharedPreferencesProvider.getLastUpdate(Constants.SHARED_PREFERENCES_LAST_UPDATE_REPORT)==0 ||
                System.currentTimeMillis()- mSharedPreferencesProvider.getLastUpdate(Constants.SHARED_PREFERENCES_LAST_UPDATE_REPORT) > Constants.HOUR &&
                isConnected) {
            fetchReports();
            mSharedPreferencesProvider.setLastUpdate(System.currentTimeMillis(), Constants.SHARED_PREFERENCES_LAST_UPDATE_REPORT);
        }
        else
            getReportsFromDatabase();
    }

    public void getBlogPostList(Boolean isConnected) {

        if(mSharedPreferencesProvider.getLastUpdate(Constants.SHARED_PREFERENCES_LAST_UPDATE_BLOGPOST)==0 ||
                System.currentTimeMillis()- mSharedPreferencesProvider.getLastUpdate(Constants.SHARED_PREFERENCES_LAST_UPDATE_BLOGPOST) > Constants.HOUR &&
                        isConnected) {
            fetchBlogPosts();
            mSharedPreferencesProvider.setLastUpdate(System.currentTimeMillis(), Constants.SHARED_PREFERENCES_LAST_UPDATE_BLOGPOST);
        }
        else
            getBlogPostsFromDatabase();
    }

    public void refreshAll(Boolean isConnected) {
        if(isConnected) {
            new Thread(() -> {
                mArticleDao.deleteAll();
                count = 0;
                fetchArticles();
                fetchReports();
                fetchBlogPosts();
            }).start();
        }
    }

    public void clearArticles() {

        databaseOperations.deleteAll();
    }

    private void fetchArticles() {

        Call<List<Article>> articleResponseCall = mArticleApiService.getArticles(4, count);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Article> responseList = new ResponseList<>();
                    List<Article> articleList = response.body();
                    for (Article article : articleList) {
                        article.setArticleType(ArticleType.ARTICLE);
                    }
                    databaseOperations.saveList(articleList);
                    responseList.setResults(articleList);
                    mArticleListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + articleList.size() + " articles.");
                    count +=4;
                } else {
                    ResponseList<Article> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mArticleListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                ResponseList<Article> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mArticleListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchNewArticles() {
        Call<List<Article>> articleResponseCall = mArticleApiService.getArticles(4, count);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Article> responseList = new ResponseList<>();
                    List<Article> articleList = response.body();
                    for (Article article : articleList) {
                        article.setArticleType(ArticleType.ARTICLE);
                    }
                    databaseOperations.saveList(articleList);
                    if (mArticleListLiveData.getValue() != null) {
                        List<Article> currentArticleList = mArticleListLiveData.getValue().getResults();
                        currentArticleList.addAll(articleList);
                        responseList.setResults(currentArticleList);
                    }
                    mArticleListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + articleList.size() + " current articles.");
                    count +=4;
                } else {
                    ResponseList<Article> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mArticleListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                ResponseList<Article> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mArticleListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchArticleById(int id) {

        Call<Article> articleResponseCall = mArticleApiService.getArticle(id);
        articleResponseCall.enqueue(new Callback<Article>() {

            @Override
            public void onResponse(@NonNull Call<Article> call,
                                   @NonNull Response<Article> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Article> responseList = new ResponseList<>();
                    List<Article> articleList = new ArrayList<>();
                    Article article = response.body();
                    article.setArticleType(ArticleType.ARTICLE);
                    databaseOperations.saveValue(article);
                    articleList.add(article);
                    responseList.setResults(articleList);
                    mArticleListLiveData.postValue(responseList);
                    Log.d(TAG, article.getTitle());
                } else {
                    ResponseList<Article> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mArticleListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Article> call, @NonNull Throwable t) {

                ResponseList<Article> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mArticleListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchArticlesByLaunchId(String id) {

        Call<List<Article>> articleResponseCall = mArticleApiService.getLaunchArticles(id);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Article> responseList = new ResponseList<>();
                    List<Article> articleList = response.body();
                    for (Article article : articleList) {
                        article.setArticleType(ArticleType.ARTICLE);
                    }
                    databaseOperations.saveList(articleList);
                    responseList.setResults(articleList);
                    mArticleListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + articleList.size() + " articles.");
                } else {
                    ResponseList<Article> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mArticleListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                ResponseList<Article> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mArticleListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchArticlesByEventId(int id) {

        Call<List<Article>> articleResponseCall = mArticleApiService.getEventArticles(id);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Article> responseList = new ResponseList<>();
                    List<Article> articleList = response.body();
                    for (Article article : articleList) {
                        article.setArticleType(ArticleType.ARTICLE);
                    }
                    databaseOperations.saveList(articleList);
                    responseList.setResults(articleList);
                    mArticleListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + articleList.size() + " articles.");
                } else {
                    ResponseList<Article> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mArticleListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                ResponseList<Article> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mArticleListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchReports() {

        Call<List<Article>> articleResponseCall = mArticleApiService.getReports(2, count);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Article> responseList = new ResponseList<>();
                    List<Article> reportList = response.body();
                    for (Article report : reportList) {
                        report.setArticleType(ArticleType.REPORT);
                    }
                    databaseOperations.saveList(reportList);
                    responseList.setResults(reportList);
                    mReportListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + reportList.size() + " reports.");
                } else {
                    ResponseList<Article> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mReportListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                ResponseList<Article> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mReportListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchReportById(int id) {

        Call<Article> ArticleResponseCall = mArticleApiService.getReport(id);
        ArticleResponseCall.enqueue(new Callback<Article>() {

            @Override
            public void onResponse(@NonNull Call<Article> call,
                                   @NonNull Response<Article> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Article> responseList = new ResponseList<>();
                    List<Article> reportList = new ArrayList<>();
                    Article report = response.body();
                    report.setArticleType(ArticleType.REPORT);
                    databaseOperations.saveValue(report);
                    reportList.add(report);
                    responseList.setResults(reportList);
                    mReportListLiveData.postValue(responseList);
                    Log.d(TAG, report.getTitle());
                } else {
                    ResponseList<Article> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mReportListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Article> call, @NonNull Throwable t) {

                ResponseList<Article> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mReportListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchBlogPosts() {

        Call<List<Article>> articleResponseCall = mArticleApiService.getBlogPosts(2, count);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Article> responseList = new ResponseList<>();
                    List<Article> blogPostList = response.body();
                    for (Article blogPost : blogPostList) {
                        blogPost.setArticleType(ArticleType.BLOG);
                    }
                    databaseOperations.saveList(blogPostList);
                    responseList.setResults(blogPostList);
                    mBlogPostListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + blogPostList.size() + " blog posts.");
                } else {
                    ResponseList<Article> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mBlogPostListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                ResponseList<Article> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mBlogPostListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchBlogPostById(int id) {

        Call<Article> articleResponseCall = mArticleApiService.getBlogPost(id);
        articleResponseCall.enqueue(new Callback<Article>() {

            @Override
            public void onResponse(@NonNull Call<Article> call,
                                   @NonNull Response<Article> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Article> responseList = new ResponseList<>();
                    List<Article> blogPostList = new ArrayList<>();
                    Article blogPost = response.body();
                    blogPost.setArticleType(ArticleType.BLOG);
                    databaseOperations.saveValue(blogPost);
                    blogPostList.add(blogPost);
                    responseList.setResults(blogPostList);
                    Log.d(TAG, blogPost.getTitle());
                } else {
                    ResponseList<Article> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mBlogPostListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Article> call, @NonNull Throwable t) {

                ResponseList<Article> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mBlogPostListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchBlogPostsByLaunchId(String id) {

        Call<List<Article>> articleResponseCall = mArticleApiService.getLaunchBlogPost(id);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Article> responseList = new ResponseList<>();
                    List<Article> blogPostList = response.body();
                    for (Article blogPost : blogPostList) {
                        blogPost.setArticleType(ArticleType.BLOG);
                    }
                    databaseOperations.saveList(blogPostList);
                    responseList.setResults(blogPostList);
                    mBlogPostListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + blogPostList.size() + " blog posts.");
                } else {
                    ResponseList<Article> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mBlogPostListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                ResponseList<Article> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mBlogPostListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchBlogPostsByEventId(int id) {

        Call<List<Article>> articleResponseCall = mArticleApiService.getEventBlogPosts(id);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Article> responseList = new ResponseList<>();
                    List<Article> blogPostList = response.body();
                    for (Article blogPost : blogPostList) {
                        blogPost.setArticleType(ArticleType.BLOG);
                    }
                    databaseOperations.saveList(blogPostList);
                    responseList.setResults(blogPostList);
                    mBlogPostListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + blogPostList.size() + " blog posts.");
                } else {
                    ResponseList<Article> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mBlogPostListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                ResponseList<Article> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mBlogPostListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void getArticlesFromDatabase() {

        new Thread(() -> {
            ResponseList<Article> responseList = new ResponseList<>();
            responseList.setResults(mArticleDao.getArticles());
            if (responseList.getResults() == null) {
                responseList.setError(true);
                responseList.setMessage("Error reading from db");
            }
            mArticleListLiveData.postValue(responseList);
        }).start();
    }

    private void getReportsFromDatabase() {

        new Thread(() -> {
            ResponseList<Article> responseList = new ResponseList<>();
            responseList.setResults(mArticleDao.getReports());
            if (responseList.getResults() == null) {
                responseList.setError(true);
                responseList.setMessage("Error reading from db");
            }
            mReportListLiveData.postValue(responseList);
        }).start();
    }

    private void getBlogPostsFromDatabase() {

        new Thread(() -> {
            ResponseList<Article> responseList = new ResponseList<>();
            responseList.setResults(mArticleDao.getBlogPosts());
            if (responseList.getResults() == null) {
                responseList.setError(true);
                responseList.setMessage("Error reading from db");
            }
            mBlogPostListLiveData.postValue(responseList);
        }).start();
    }
}
