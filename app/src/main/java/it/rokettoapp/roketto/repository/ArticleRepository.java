package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.Article;
import it.rokettoapp.roketto.service.ArticleApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

    private static final String TAG = "ArticleRepository";
    private final ArticleApiService articleApiService;

    public ArticleRepository() {

        this.articleApiService = ServiceLocator.getsInstance().getArticleApiService();
    }

    public void fetchArticles() {

        Call<List<Article>> articleResponseCall = articleApiService.getArticles();
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

    public void fetchArticleById(int id) {

        Call<Article> articleResponseCall = articleApiService.getArticle(id);
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

    public void fetchArticleByLaunchId(String id) {

        Call<List<Article>> articleResponseCall = articleApiService.getLaunchArticles(id);
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

    public void fetchArticleByEventId(int id) {

        Call<List<Article>> articleResponseCall = articleApiService.getEventArticles(id);
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
}
