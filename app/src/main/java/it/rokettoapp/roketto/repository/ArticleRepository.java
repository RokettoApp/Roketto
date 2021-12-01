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
import it.rokettoapp.roketto.service.ArticleApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

    private static final String TAG = "ArticleRepository";
    private final ArticleApiService mArticleApiService;
    private final ArticleDao mArticleDao;
    private final MutableLiveData<List<Article>> mArticleListLiveData;
    private final MutableLiveData<List<Article>> mReportListLiveData;
    private final MutableLiveData<List<Article>> mBlogPostListLiveData;
    int count;

    public ArticleRepository(Application application) {

        this.mArticleApiService = ServiceLocator.getInstance().getArticleApiService();
        mArticleDao = RokettoDatabase.getDatabase(application).articleDao();
        mArticleListLiveData = new MutableLiveData<>();
        mReportListLiveData = new MutableLiveData<>();
        mBlogPostListLiveData = new MutableLiveData<>();
        count = 0;
        RokettoDatabase.databaseWriteExecutor.execute(mArticleDao::deleteAll);
    }

    public MutableLiveData<List<Article>> fetchArticles() {

        fetchArticlesFromApi();
        return mArticleListLiveData;
    }

    public MutableLiveData<List<Article>> fetchReports() {

        fetchReportsFromApi();
        return mReportListLiveData;
    }

    public MutableLiveData<List<Article>> fetchBlogPosts() {

        fetchBlogPostsFromApi();
        return mBlogPostListLiveData;
    }

    public void refreshAll() {

        new Thread(() -> {
            mArticleDao.deleteAll();
            fetchArticlesFromApi();
            fetchReportsFromApi();
            fetchBlogPostsFromApi();
        }).start();
        count += 6;
    }

    private void fetchArticlesFromApi() {

        Call<List<Article>> articleResponseCall = mArticleApiService.getArticles(2, count);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> articleList = response.body();
                    mArticleListLiveData.postValue(articleList);
                    saveOnDatabase(articleList);
                    Log.d(TAG, "Retrieved " + articleList.size() + " articles.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchArticleById(int id) {

        Call<Article> articleResponseCall = mArticleApiService.getArticle(id);
        articleResponseCall.enqueue(new Callback<Article>() {

            @Override
            public void onResponse(@NonNull Call<Article> call,
                                   @NonNull Response<Article> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Article article = response.body();
                    Log.d(TAG, article.getTitle());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Article> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchArticlesByLaunchId(String id) {

        Call<List<Article>> articleResponseCall = mArticleApiService.getLaunchArticles(id);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> articleList = response.body();
                    StringBuilder debugString = new StringBuilder();
                    for (Article article : articleList) {
                        debugString.append(article.getTitle()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchArticlesByEventId(int id) {

        Call<List<Article>> articleResponseCall = mArticleApiService.getEventArticles(id);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> articleList = response.body();
                    StringBuilder debugString = new StringBuilder();
                    for (Article article : articleList) {
                        debugString.append(article.getTitle()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchReportsFromApi() {

        Call<List<Article>> articleResponseCall = mArticleApiService.getReports(2, count);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> reportList = response.body();
                    mReportListLiveData.postValue(reportList);
                    saveOnDatabase(reportList);
                    Log.d(TAG, "Retrieved " + reportList.size() + " reports.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchReportById(int id) {

        Call<Article> agencyResponseCall = mArticleApiService.getReport(id);
        agencyResponseCall.enqueue(new Callback<Article>() {

            @Override
            public void onResponse(@NonNull Call<Article> call,
                                   @NonNull Response<Article> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Article article = response.body();
                    Log.d(TAG, article.getTitle());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Article> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchBlogPostsFromApi() {

        Call<List<Article>> articleResponseCall = mArticleApiService.getBlogPosts(2, count);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> blogPostList = response.body();
                    saveOnDatabase(blogPostList);
                    mBlogPostListLiveData.postValue(blogPostList);
                    Log.d(TAG, "Retrieved " + blogPostList.size() + " blog posts.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchBlogPostById(int id) {

        Call<Article> articleResponseCall = mArticleApiService.getBlogPost(id);
        articleResponseCall.enqueue(new Callback<Article>() {

            @Override
            public void onResponse(@NonNull Call<Article> call,
                                   @NonNull Response<Article> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Article article = response.body();
                    Log.d(TAG, article.getTitle());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Article> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchBlogPostsByLaunchId(String id) {

        Call<List<Article>> articleResponseCall = mArticleApiService.getLaunchBlogPost(id);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> articleList = response.body();
                    StringBuilder debugString = new StringBuilder();
                    for (Article article : articleList) {
                        debugString.append(article.getTitle()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchBlogPostsByEventId(int id) {

        Call<List<Article>> articleResponseCall = mArticleApiService.getEventBlogPosts(id);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> articleList = response.body();
                    StringBuilder debugString = new StringBuilder();
                    for (Article article : articleList) {
                        debugString.append(article.getTitle()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void saveOnDatabase(List<Article> articleList) {

        RokettoDatabase.databaseWriteExecutor.execute(() ->
            mArticleDao.insertArticleList(articleList));
    }

    public List<Article> getAllFromDatabase() {

        List<Article> articleList = new ArrayList<>();
        new Thread(() -> {
            List<Article> results = mArticleDao.getAll();
            if (results != null)
                articleList.addAll(results);
        }).start();
        return articleList;
    }
}
