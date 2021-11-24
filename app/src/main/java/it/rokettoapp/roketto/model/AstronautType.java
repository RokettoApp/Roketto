package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class AstronautType {

    @SerializedName("name")
    private String mName;

    public AstronautType(String name) {

        this.mName = name;
    }

    public String getName() {

        return mName;
    }

    public void setName(String name) {

        this.mName = name;
    }
}
