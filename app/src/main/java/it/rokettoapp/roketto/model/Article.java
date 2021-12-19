package it.rokettoapp.roketto.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

@Entity(tableName = "article")
public class Article {

    @PrimaryKey
    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("imageUrl")
    private String mImageUrl;

    @SerializedName("newsSite")
    private String mSource;

    @SerializedName("summary")
    private String mSummary;

    @SerializedName("publishedAt")
    private Date mPublishedAt;

    @SerializedName("updatedAt")
    private Date mUpdatedAt;

    @SerializedName("featured")
    private boolean mFeatured;

    @SerializedName("launches")
    private List<ArticleLaunch> mLaunchList;

    @SerializedName("events")
    private List<ArticleEvent> mEventList;

    private ArticleType mArticleType;

    public Article(int id, String title, String url, String imageUrl, String source,
                   String summary, Date publishedAt, Date updatedAt, boolean featured,
                   List<ArticleLaunch> launchList, List<ArticleEvent> eventList) {

        this.mId = id;
        this.mTitle = title;
        this.mUrl = url;
        this.mImageUrl = imageUrl;
        this.mSource = source;
        this.mSummary = summary;
        this.mPublishedAt = publishedAt;
        this.mUpdatedAt = updatedAt;
        this.mFeatured = featured;
        this.mLaunchList = launchList;
        this.mEventList = eventList;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public String getTitle() {

        return mTitle;
    }

    public void setTitle(String title) {

        this.mTitle = title;
    }

    public String getUrl() {

        return mUrl;
    }

    public void setUrl(String url) {

        this.mUrl = url;
    }

    public String getImageUrl() {

        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.mImageUrl = imageUrl;
    }

    public String getSource() {

        return mSource;
    }

    public void setNewsSite(String newsSite) {

        this.mSource = newsSite;
    }

    public String getSummary() {

        return mSummary;
    }

    public void setSummary(String summary) {

        this.mSummary = summary;
    }

    public Date getPublishedAt() {

        return mPublishedAt;
    }

    public void setPublishedAt(Date publishedAt) {

        this.mPublishedAt = publishedAt;
    }

    public Date getUpdatedAt() {

        return mUpdatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {

        this.mUpdatedAt = updatedAt;
    }

    public boolean isFeatured() {

        return mFeatured;
    }

    public void setFeatured(boolean featured) {

        this.mFeatured = featured;
    }

    public List<ArticleLaunch> getLaunchList() {

        return mLaunchList;
    }

    public void setLaunchList(List<ArticleLaunch> launchList) {

        this.mLaunchList = launchList;
    }

    public List<ArticleEvent> getEventList() {

        return mEventList;
    }

    public void setEventList(List<ArticleEvent> eventList) {

        this.mEventList = eventList;
    }

    public ArticleType getArticleType() {

        return mArticleType;
    }

    public void setArticleType(ArticleType articleType) {

        this.mArticleType = articleType;
    }
}
