package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class AstronautType {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    public AstronautType(int id, String name) {

        this.mId = id;
        this.mName = name;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public String getName() {

        return mName;
    }

    public void setName(String name) {

        this.mName = name;
    }
}
