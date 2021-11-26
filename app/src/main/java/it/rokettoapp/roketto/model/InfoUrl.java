package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class InfoUrl {

    @SerializedName("priority")
    int mPriority;

    @SerializedName("title")
    String mTitle;

    @SerializedName("description")
    String mDescription;

    @SerializedName("feature_image")
    String mFeatureImage;

    @SerializedName("url")
    String mUrl;

    public InfoUrl(int priority, String title, String description, String featureImage, String url) {

        this.mPriority = priority;
        this.mTitle = title;
        this.mDescription = description;
        this.mFeatureImage = featureImage;
        this.mUrl = url;
    }

    public int getPriority() {

        return mPriority;
    }

    public void setPriority(int priority) {

        this.mPriority = priority;
    }

    public String getTitle() {

        return mTitle;
    }

    public void setTitle(String title) {

        this.mTitle = title;
    }

    public String getDescription() {

        return mDescription;
    }

    public void setDescription(String description) {

        this.mDescription = description;
    }

    public String getFeatureImage() {

        return mFeatureImage;
    }

    public void setFeatureImage(String featureImage) {

        this.mFeatureImage = featureImage;
    }

    public String getUrl() {

        return mUrl;
    }

    public void setUrl(String url) {

        this.mUrl = url;
    }
}
