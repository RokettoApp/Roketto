package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class Landing {

    @SerializedName("id")
    private int mId;

    @SerializedName("attempt")
    private boolean mAttempt;

    @SerializedName("success")
    private boolean mSuccess;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("location")
    private LandingLocation mLocation;

    @SerializedName("type")
    private LandingType mType;

    public Landing(int id, boolean attempt, boolean success, String description,
                   LandingLocation location, LandingType type) {

        this.mId = id;
        this.mAttempt = attempt;
        this.mSuccess = success;
        this.mDescription = description;
        this.mLocation = location;
        this.mType = type;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public boolean isAttempt() {

        return mAttempt;
    }

    public void setAttempt(boolean attempt) {

        this.mAttempt = attempt;
    }

    public boolean isSuccess() {

        return mSuccess;
    }

    public void setSuccess(boolean success) {

        this.mSuccess = success;
    }

    public String getDescription() {

        return mDescription;
    }

    public void setDescription(String description) {

        this.mDescription = description;
    }

    public LandingLocation getLocation() {

        return mLocation;
    }

    public void setLocation(LandingLocation location) {

        this.mLocation = location;
    }

    public LandingType getType() {

        return mType;
    }

    public void setType(LandingType type) {

        this.mType = type;
    }
}
