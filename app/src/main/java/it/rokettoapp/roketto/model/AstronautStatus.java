package it.rokettoapp.roketto.model;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

public class AstronautStatus {

    @Ignore
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    public AstronautStatus(String name) {

        this.mName = name;
    }

    public AstronautStatus(int id, String name) {

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
