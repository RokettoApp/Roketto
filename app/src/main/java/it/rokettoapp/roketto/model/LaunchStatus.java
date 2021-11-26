package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class LaunchStatus {

    @SerializedName("id")
    int mId;

    @SerializedName("name")
    String mName;

    @SerializedName("abbrev")
    String mAbbreviation;

    @SerializedName("description")
    String mDescrption;

    public LaunchStatus(int id, String name, String abbreviation, String descrption) {

        this.mId = id;
        this.mName = name;
        this.mAbbreviation = abbreviation;
        this.mDescrption = descrption;
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

    public String getDescrption() {

        return mDescrption;
    }

    public void setDescrption(String descrption) {

        this.mDescrption = descrption;
    }
}
