package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class ArticleLaunch {

    @SerializedName("id")
    String mId;

    @SerializedName("provider")
    String mProvider;

    public ArticleLaunch(String id, String provider) {

        this.mId = id;
        this.mProvider = provider;
    }

    public String getId() {

        return mId;
    }

    public void setId(String id) {

        this.mId = id;
    }

    public String getProvider() {

        return mProvider;
    }

    public void setProvider(String provider) {

        this.mProvider = provider;
    }
}
