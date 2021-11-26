package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Program {

    @SerializedName("id")
    int mId;

    @SerializedName("name")
    String mName;

    @SerializedName("description")
    String mDescription;

    @SerializedName("agencies")
    List<Agency> mAgencies;

    @SerializedName("image_url")
    String mImageUrl;

    @SerializedName("start_date")
    Date mStartDate;

    @SerializedName("end_date")
    Date mEndDate;

    @SerializedName("info_url")
    String mInfoUrl;

    @SerializedName("wiki_url")
    String mWikipedia;

    @SerializedName("mission_patches")
    List<MissionPatch> mMissionPatch;

    public Program(int id, String name, String description, List<Agency> agencies, String imageUrl,
                   Date startDate, Date endDate, String infoUrl, String wikipedia,
                   List<MissionPatch> missionPatch) {

        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mAgencies = agencies;
        this.mImageUrl = imageUrl;
        this.mStartDate = startDate;
        this.mEndDate = endDate;
        this.mInfoUrl = infoUrl;
        this.mWikipedia = wikipedia;
        this.mMissionPatch = missionPatch;
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

    public String getDescription() {

        return mDescription;
    }

    public void setDescription(String description) {

        this.mDescription = description;
    }

    public List<Agency> getAgencies() {

        return mAgencies;
    }

    public void setAgencies(List<Agency> agencies) {

        this.mAgencies = agencies;
    }

    public String getImageUrl() {

        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.mImageUrl = imageUrl;
    }

    public Date getStartDate() {

        return mStartDate;
    }

    public void setStartDate(Date startDate) {

        this.mStartDate = startDate;
    }

    public Date getEndDate() {

        return mEndDate;
    }

    public void setEndDate(Date endDate) {

        this.mEndDate = endDate;
    }

    public String getInfoUrl() {

        return mInfoUrl;
    }

    public void setInfoUrl(String infoUrl) {

        this.mInfoUrl = infoUrl;
    }

    public String getWikipedia() {

        return mWikipedia;
    }

    public void setWikipedia(String wikipedia) {

        this.mWikipedia = wikipedia;
    }

    public List<MissionPatch> getMissionPatch() {

        return mMissionPatch;
    }

    public void setMissionPatch(List<MissionPatch> missionPatch) {

        this.mMissionPatch = missionPatch;
    }
}
