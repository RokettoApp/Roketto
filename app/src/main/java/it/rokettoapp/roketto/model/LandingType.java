package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class LandingType {

    @SerializedName("id")
    int mId;

    @SerializedName("name")
    String mName;

    @SerializedName("abbrev")
    String mAbbreviation;

    @SerializedName("description")
    String mDescription;

    public LandingType(int id, String name, String abbreviation, String description) {

        this.mId = id;
        this.mName = name;
        this.mAbbreviation = abbreviation;
        this.mDescription = description;
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

    public String getDescription() {

        return mDescription;
    }

    public void setDescription(String description) {

        this.mDescription = description;
    }
}
