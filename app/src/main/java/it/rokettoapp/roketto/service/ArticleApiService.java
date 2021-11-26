package it.rokettoapp.roketto.service;

import java.util.List;

import it.rokettoapp.roketto.model.Article;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArticleApiService {

    @GET(Constants.ARTICLES_ENDPOINT)
    Call<List<Article>> getArticles();

    @GET(Constants.ARTICLE_ENDPOINT)
    Call<Article> getArticle(@Path("id") int id);

    @GET(Constants.ARTICLE_LAUNCH_ENDPOINT)
    Call<List<Article>> getLaunchArticles(@Path("id") String id);

    @GET(Constants.ARTICLE_EVENT_ENDPOINT)
    Call<List<Article>> getEventArticles(@Path("id") int id);
}
