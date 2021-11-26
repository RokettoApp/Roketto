package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Article {

    @SerializedName("id")
    int mId;

    @SerializedName("title")
    String mTitle;

    @SerializedName("url")
    String mUrl;

    @SerializedName("imageUrl")
    String mImageUrl;

    @SerializedName("newsSite")
    String mNewsSite;

    @SerializedName("summary")
    String mSummary;

    @SerializedName("publishedAt")
    Date mPublishedAt;

    @SerializedName("updatedAt")
    Date mUpdatedAt;

    @SerializedName("featured")
    boolean mFeatured;

    @SerializedName("launches")
    List<ArticleLaunch> mLaunchList;

    @SerializedName("events")
    List<ArticleEvent> mEventList;

    public Article(int id, String title, String url, String imageUrl, String newsSite,
                   String summary, Date publishedAt, Date updatedAt, boolean featured,
                   List<ArticleLaunch> launchList, List<ArticleEvent> eventList) {

        this.mId = id;
        this.mTitle = title;
        this.mUrl = url;
        this.mImageUrl = imageUrl;
        this.mNewsSite = newsSite;
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

    public String getNewsSite() {

        return mNewsSite;
    }

    public void setNewsSite(String newsSite) {

        this.mNewsSite = newsSite;
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

        this.mPublishedAt = mPublishedAt;
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
}
