package it.rokettoapp.roketto.service;

import java.util.List;

import it.rokettoapp.roketto.model.Article;
import it.rokettoapp.roketto.model.Spacecraft;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArticleApiService {

    @GET(Constants.ARTICLES_ENDPOINT)
    Call<List<Article>> getArticles();

    @GET(Constants.ARTICLE_ENDPOINT)
    Call<Article> getArticle(@Path("id") int id);

    @GET(Constants.ARTICLES_LAUNCH_ENDPOINT)
    Call<List<Article>> getLaunchArticles(@Path("id") String id);

    @GET(Constants.ARTICLES_EVENT_ENDPOINT)
    Call<List<Article>> getEventArticles(@Path("id") int id);

    @GET(Constants.REPORTS_ENDPOINT)
    Call<List<Article>> getReports();

    @GET(Constants.REPORT_ENDPOINT)
    Call<Article> getReport(@Path("id") int id);

    @GET(Constants.BLOGS_ENDPOINT)
    Call<List<Article>> getBlogPosts();

    @GET(Constants.BLOG_ENDPOINT)
    Call<Article> getBlogPost(@Path("id") int id);

    @GET(Constants.BLOGS_LAUNCH_ENDPOINT)
    Call<List<Article>> getLaunchBlogPost(@Path("id") String id);

    @GET(Constants.BLOGS_EVENT_ENDPOINT)
    Call<List<Article>> getEventBlogPosts(@Path("id") int id);
}
