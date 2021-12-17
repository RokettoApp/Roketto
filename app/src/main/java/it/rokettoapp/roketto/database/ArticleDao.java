package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Article;

@Dao
public interface ArticleDao extends GenericDao<Integer, Article> {

    @Override
    @Query("SELECT * FROM article")
    List<Article> getAll();

    @Query("SELECT * FROM article WHERE mArticleType = 'ARTICLE'")
    List<Article> getArticles();

    @Query("SELECT * FROM article WHERE mArticleType = 'REPORT'")
    List<Article> getReports();

    @Query("SELECT * FROM article WHERE mArticleType = 'BLOG'")
    List<Article> getBlogPosts();

    @Override
    @Query("SELECT * " +
           "FROM article " +
           "WHERE mId = :id")
    Article getById(Integer id);

    @Override
    @Insert
    void insertList(List<Article> articleList);

    @Override
    @Insert
    void insert(Article article);

    @Override
    @Delete
    void delete(Article article);

    @Override
    @Query("DELETE FROM article")
    void deleteAll();
}
