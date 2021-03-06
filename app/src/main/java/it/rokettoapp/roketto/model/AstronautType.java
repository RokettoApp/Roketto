package it.rokettoapp.roketto.model;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AstronautType implements Serializable {

    @Ignore
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    public AstronautType(String name) {

        this.mName = name;
    }

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
