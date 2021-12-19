package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArticleEvent implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("provider")
    private String mProvider;

    public ArticleEvent(int id) {
        this.mId = id;
    }

    public ArticleEvent(int id, String provider) {

        this.mId = id;
        this.mProvider = provider;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public String getProvider() {

        return mProvider;
    }

    public void setProvider(String provider) {

        this.mProvider = provider;
    }
}
