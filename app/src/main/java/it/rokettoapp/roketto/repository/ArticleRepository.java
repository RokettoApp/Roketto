package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.database.ArticleDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Article;
import it.rokettoapp.roketto.model.ArticleType;
import it.rokettoapp.roketto.service.ArticleApiService;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

    private static final String TAG = "ArticleRepository";
    private final ArticleApiService mArticleApiService;
    private final ArticleDao mArticleDao;
    private final DatabaseOperations<Integer, Article> databaseOperations;
    private final MutableLiveData<List<Article>> mArticleListLiveData;
    private final MutableLiveData<List<Article>> mReportListLiveData;
    private final MutableLiveData<List<Article>> mBlogPostListLiveData;
    int count;

    public ArticleRepository(Application application) {

        this.mArticleApiService = ServiceLocator.getInstance().getArticleApiService();
        mArticleDao = RokettoDatabase.getDatabase(application).articleDao();
        databaseOperations = new DatabaseOperations<>(mArticleDao);
        mArticleListLiveData = new MutableLiveData<>();
        mReportListLiveData = new MutableLiveData<>();
        mBlogPostListLiveData = new MutableLiveData<>();
        count = 0;
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

    public void getArticleList() {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        getArticlesFromDatabase();
//        fetchArticles();
    }

    public void getReportList() {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        getReportsFromDatabase();
//        fetchReports();
    }

    public void getBlogPostList() {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        getBlogPostsFromDatabase();
//        fetchBlogPosts();
    }

    public void refreshAll() {

        new Thread(() -> {
            mArticleDao.deleteAll();
            fetchArticles();
            fetchReports();
            fetchBlogPosts();
        }).start();
        count += 6;
    }

    private void fetchArticles() {

        Call<List<Article>> articleResponseCall = mArticleApiService.getArticles(2, count);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> articleList = response.body();
                    for (Article article : articleList) {
                        article.setArticleType(ArticleType.ARTICLE);
                    }
                    databaseOperations.saveList(articleList);
                    mArticleListLiveData.postValue(articleList);
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

    private void fetchArticleById(int id) {

        Call<Article> articleResponseCall = mArticleApiService.getArticle(id);
        articleResponseCall.enqueue(new Callback<Article>() {

            @Override
            public void onResponse(@NonNull Call<Article> call,
                                   @NonNull Response<Article> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Article article = response.body();
                    article.setArticleType(ArticleType.ARTICLE);
                    databaseOperations.saveValue(article);
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

    private void fetchArticlesByLaunchId(String id) {

        Call<List<Article>> articleResponseCall = mArticleApiService.getLaunchArticles(id);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> articleList = response.body();
                    for (Article article : articleList) {
                        article.setArticleType(ArticleType.ARTICLE);
                    }
                    databaseOperations.saveList(articleList);
                    mArticleListLiveData.postValue(articleList);
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

    private void fetchArticlesByEventId(int id) {

        Call<List<Article>> articleResponseCall = mArticleApiService.getEventArticles(id);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> articleList = response.body();
                    for (Article article : articleList) {
                        article.setArticleType(ArticleType.ARTICLE);
                    }
                    databaseOperations.saveList(articleList);
                    mArticleListLiveData.postValue(articleList);
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

    private void fetchReports() {

        Call<List<Article>> articleResponseCall = mArticleApiService.getReports(2, count);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> reportList = response.body();
                    for (Article report : reportList) {
                        report.setArticleType(ArticleType.REPORT);
                    }
                    databaseOperations.saveList(reportList);
                    mReportListLiveData.postValue(reportList);
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

    private void fetchReportById(int id) {

        Call<Article> agencyResponseCall = mArticleApiService.getReport(id);
        agencyResponseCall.enqueue(new Callback<Article>() {

            @Override
            public void onResponse(@NonNull Call<Article> call,
                                   @NonNull Response<Article> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Article report = response.body();
                    report.setArticleType(ArticleType.REPORT);
                    databaseOperations.saveValue(report);
                    Log.d(TAG, report.getTitle());
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

    private void fetchBlogPosts() {

        Call<List<Article>> articleResponseCall = mArticleApiService.getBlogPosts(2, count);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> blogPostList = response.body();
                    for (Article blogPost : blogPostList) {
                        blogPost.setArticleType(ArticleType.BLOG);
                    }
                    databaseOperations.saveList(blogPostList);
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

    private void fetchBlogPostById(int id) {

        Call<Article> articleResponseCall = mArticleApiService.getBlogPost(id);
        articleResponseCall.enqueue(new Callback<Article>() {

            @Override
            public void onResponse(@NonNull Call<Article> call,
                                   @NonNull Response<Article> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Article blogPost = response.body();
                    blogPost.setArticleType(ArticleType.BLOG);
                    databaseOperations.saveValue(blogPost);
                    Log.d(TAG, blogPost.getTitle());
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

    private void fetchBlogPostsByLaunchId(String id) {

        Call<List<Article>> articleResponseCall = mArticleApiService.getLaunchBlogPost(id);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> blogPostList = response.body();
                    for (Article blogPost : blogPostList) {
                        blogPost.setArticleType(ArticleType.BLOG);
                    }
                    databaseOperations.saveList(blogPostList);
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

    private void fetchBlogPostsByEventId(int id) {

        Call<List<Article>> articleResponseCall = mArticleApiService.getEventBlogPosts(id);
        articleResponseCall.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(@NonNull Call<List<Article>> call,
                                   @NonNull Response<List<Article>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Article> blogPostList = response.body();
                    for (Article blogPost : blogPostList) {
                        blogPost.setArticleType(ArticleType.BLOG);
                    }
                    databaseOperations.saveList(blogPostList);
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

    private void getArticlesFromDatabase() {

        new Thread(() -> mArticleListLiveData.postValue(mArticleDao.getArticles())).start();
    }

    private void getReportsFromDatabase() {

        new Thread(() -> mReportListLiveData.postValue(mArticleDao.getReports())).start();
    }

    private void getBlogPostsFromDatabase() {

        new Thread(() -> mBlogPostListLiveData.postValue(mArticleDao.getBlogPosts())).start();
    }
}
