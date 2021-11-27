package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class VidUrl {

    @SerializedName("priority")
    private int mPriority;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("feature_image")
    private String mFeatureImage;

    @SerializedName("url")
    private String mUrl;

    public VidUrl(int priority, String title, String description, String featureImage, String url) {

        this.mPriority = priority;
        this.mTitle = title;
        this.mDescription = description;
        this.mFeatureImage = featureImage;
        this.mUrl = url;
    }

    public int getPriority() {

        return mPriority;
    }

    public void setmriority(int priority) {

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
