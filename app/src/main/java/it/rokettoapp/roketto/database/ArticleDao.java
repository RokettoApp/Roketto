package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Article;

@Dao
public interface ArticleDao {
    @Query("SELECT * FROM favorite_article")
    List<Article> getAll();

    @Insert
    void insertAgencyList(List<Article> articleList);

    @Insert
    void insertAgency(Article article);

    @Delete
    void delete(Article article);

    @Query("DELETE FROM favorite_article")
    void deleteAll();
}
