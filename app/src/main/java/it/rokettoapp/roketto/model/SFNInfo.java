package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SFNInfo implements Serializable {

    @SerializedName("version")
    private String mApiVersion;

    @SerializedName("newsSites")
    private List<String> mSources;

    public SFNInfo(String apiVersion, List<String> sources) {

        this.mApiVersion = apiVersion;
        this.mSources = sources;
    }

    public String getApiVersion() {

        return mApiVersion;
    }

    public void setApiVersion(String apiVersion) {

        this.mApiVersion = apiVersion;
    }

    public List<String> getSources() {

        return mSources;
    }

    public void setSources(List<String> sources) {

        this.mSources = sources;
    }
}
