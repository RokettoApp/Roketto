package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class AstronautStatus {

    @SerializedName("name")
    private String mName;

    public AstronautStatus(String name) {

        this.mName = name;
    }

    public String getName() {

        return mName;
    }

    public void setName(String name) {

        this.mName = name;
    }
}
