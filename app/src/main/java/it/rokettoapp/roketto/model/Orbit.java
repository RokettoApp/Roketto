package it.rokettoapp.roketto.model;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Orbit implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("abbrev")
    private String mAbbreviation;

    @Ignore
    public Orbit(int id) {

        this.mId = id;
    }

    public Orbit(int id, String name, String abbreviation) {

        this.mId = id;
        this.mName = name;
        this.mAbbreviation = abbreviation;
    }

    public static Orbit buildMinOrbit(Orbit orbit) {

        Orbit minOrbit = new Orbit(orbit.getId());
        minOrbit.setName(orbit.getName());
        return minOrbit;
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

    public String getAbbreviation() {

        return mAbbreviation;
    }

    public void setAbbreviation(String abbreviation) {

        this.mAbbreviation = abbreviation;
    }
}
