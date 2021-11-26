package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class ArticleEvent {

    @SerializedName("id")
    int mId;

    @SerializedName("provider")
    String mProvider;

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
