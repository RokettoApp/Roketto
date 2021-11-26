package it.rokettoapp.roketto.model;

import androidx.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class SpacecraftConfigurationDetail {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @Embedded(prefix = "type_")
    @SerializedName("type")
    private SpacecraftConfigType mSpacecraftConfigType;

    @Embedded(prefix = "agency_")
    @SerializedName("agency")
    private Agency mAgency;

    @SerializedName("in_use")
    private boolean mInUse;

    @SerializedName("capability")
    private String mCapability;

    @SerializedName("history")
    private String mHistory;

    @SerializedName("details")
    private String mDetails;

    @SerializedName("maiden_flight")
    private String mMaidenFlight;

    @SerializedName("height")
    private float mHeight;

    @SerializedName("diameter")
    private float mDiameter;

    @SerializedName("human_rated")
    private boolean mHumanRated;

    @SerializedName("crew_capacity")
    private int mCrewCapacity;

    @SerializedName("payload_capacity")
    private int mPayloadCapacity;

    @SerializedName("flight_life")
    private String mFlightLift;

    @SerializedName("image_url")
    private String mImageUrl;

    @SerializedName("nation_url")
    private String mNationUrl;

    @SerializedName("wiki_link")
    private String mWikipedia;

    @SerializedName("info_link")
    private String mInfoUrl;

    public SpacecraftConfigurationDetail(int id, String name, Agency agency,
                                         SpacecraftConfigType spacecraftConfigType,
                                         boolean inUse, String capability, String history,
                                         String details, String maidenFlight, float height,
                                         float diameter, boolean humanRated, int crewCapacity,
                                         int payloadCapacity, String flightLift, String imageUrl,
                                         String nationUrl, String wikipedia, String infoUrl) {

        this.mId = id;
        this.mName = name;
        this.mAgency = agency;
        this.mSpacecraftConfigType = spacecraftConfigType;
        this.mInUse = inUse;
        this.mCapability = capability;
        this.mHistory = history;
        this.mDetails = details;
        this.mMaidenFlight = maidenFlight;
        this.mHeight = height;
        this.mDiameter = diameter;
        this.mHumanRated = humanRated;
        this.mCrewCapacity = crewCapacity;
        this.mPayloadCapacity = payloadCapacity;
        this.mFlightLift = flightLift;
        this.mImageUrl = imageUrl;
        this.mNationUrl = nationUrl;
        this.mWikipedia = wikipedia;
        this.mInfoUrl = infoUrl;
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

    public SpacecraftConfigType getSpacecraftConfigType() {

        return mSpacecraftConfigType;
    }

    public void setSpacecraftConfigType(SpacecraftConfigType spacecraftConfigType) {

        this.mSpacecraftConfigType = spacecraftConfigType;
    }

    public Agency getAgency() {

        return mAgency;
    }

    public void setAgency(Agency agency) {

        this.mAgency = agency;
    }

    public boolean isInUse() {

        return mInUse;
    }

    public void setInUse(boolean inUse) {

        this.mInUse = inUse;
    }

    public String getCapability() {

        return mCapability;
    }

    public void setCapability(String capability) {

        this.mCapability = capability;
    }

    public String getHistory() {

        return mHistory;
    }

    public void setHistory(String history) {

        this.mHistory = history;
    }

    public String getDetails() {

        return mDetails;
    }

    public void setDetails(String details) {

        this.mDetails = details;
    }

    public String getMaidenFlight() {

        return mMaidenFlight;
    }

    public void setMaidenFlight(String maidenFlight) {

        this.mMaidenFlight = maidenFlight;
    }

    public float getHeight() {

        return mHeight;
    }

    public void setHeight(float height) {

        this.mHeight = height;
    }

    public float getDiameter() {

        return mDiameter;
    }

    public void setDiameter(float diameter) {

        this.mDiameter = diameter;
    }

    public boolean isHumanRated() {

        return mHumanRated;
    }

    public void setHumanRated(boolean humanRated) {

        this.mHumanRated = humanRated;
    }

    public int getCrewCapacity() {

        return mCrewCapacity;
    }

    public void setCrewCapacity(int crewCapacity) {

        this.mCrewCapacity = crewCapacity;
    }

    public int getPayloadCapacity() {

        return mPayloadCapacity;
    }

    public void setPayloadCapacity(int payloadCapacity) {

        this.mPayloadCapacity = payloadCapacity;
    }

    public String getFlightLift() {

        return mFlightLift;
    }

    public void setFlightLift(String flightLift) {

        this.mFlightLift = flightLift;
    }

    public String getImageUrl() {

        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.mImageUrl = imageUrl;
    }

    public String getNationUrl() {

        return mNationUrl;
    }

    public void setNationUrl(String nationUrl) {

        this.mNationUrl = nationUrl;
    }

    public String getWikipedia() {

        return mWikipedia;
    }

    public void setWikipedia(String wikipedia) {

        this.mWikipedia = wikipedia;
    }

    public String getInfoUrl() {

        return mInfoUrl;
    }

    public void setInfoUrl(String infoUrl) {

        this.mInfoUrl = infoUrl;
    }
}
