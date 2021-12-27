package it.rokettoapp.roketto.model;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LaunchStatus implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("abbrev")
    private String mAbbreviation;

    @SerializedName("description")
    private String mDescription;

    @Ignore
    public LaunchStatus(int id) {

        this.mId = id;
    }

    public LaunchStatus(int id, String name, String abbreviation, String description) {

        this.mId = id;
        this.mName = name;
        this.mAbbreviation = abbreviation;
        this.mDescription = description;
    }

    public static LaunchStatus buildMinLaunchStatus(LaunchStatus launchStatus) {

        LaunchStatus minLaunchStatus = new LaunchStatus(launchStatus.getId());
        minLaunchStatus.setName(launchStatus.getName());
        return minLaunchStatus;
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
