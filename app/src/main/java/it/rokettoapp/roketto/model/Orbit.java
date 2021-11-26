package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class Orbit {

    @SerializedName("id")
    int mId;

    @SerializedName("name")
    String mName;

    @SerializedName("abbrev")
    String mAbbreviation;

    public Orbit(int id, String name, String abbreviation) {

        this.mId = id;
        this.mName = name;
        this.mAbbreviation = abbreviation;
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

    public void setmbbreviation(String abbreviation) {

        this.mAbbreviation = abbreviation;
    }
}
