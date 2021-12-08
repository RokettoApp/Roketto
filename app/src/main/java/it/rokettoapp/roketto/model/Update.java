package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class Update {

    @SerializedName("id")
    private int mId;

    @SerializedName("profile_image")
    private String mProfileImage;

    @SerializedName("comment")
    private String mComment;

    @SerializedName("info_url")
    private String mInfoUrl;

    @SerializedName("created_by")
    private String mCreatedBy;

    @SerializedName("created_on")
    private String mCreatedOn;

    public Update(int id) {
        this.mId = id;
    }

    public Update(int id, String profileImage, String comment, String infoUrl, String createdBy,
                  String createdOn) {

        this.mId = id;
        this.mProfileImage = profileImage;
        this.mComment = comment;
        this.mInfoUrl = infoUrl;
        this.mCreatedBy = createdBy;
        this.mCreatedOn = createdOn;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public String getProfileImage() {

        return mProfileImage;
    }

    public void setProfileImage(String profileImage) {

        this.mProfileImage = profileImage;
    }

    public String getComment() {

        return mComment;
    }

    public void setComment(String comment) {

        this.mComment = comment;
    }

    public String getInfoUrl() {

        return mInfoUrl;
    }

    public void setInfoUrl(String infoUrl) {

        this.mInfoUrl = infoUrl;
    }

    public String getCreatedBy() {

        return mCreatedBy;
    }

    public void setCreatedBy(String createdBy) {

        this.mCreatedBy = createdBy;
    }

    public String getCreatedOn() {

        return mCreatedOn;
    }

    public void setCreatedOn(String createdOn) {

        this.mCreatedOn = createdOn;
    }
}
