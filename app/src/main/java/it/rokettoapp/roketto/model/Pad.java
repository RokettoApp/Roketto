package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class Pad {

    @SerializedName("id")
    int mId;

    @SerializedName("agency_id")
    int mAgencyId;

    @SerializedName("name")
    String mName;

    @SerializedName("info_url")
    String mInfoUrl;

    @SerializedName("wiki_url")
    String mWikipedia;

    @SerializedName("map_url")
    String mMapUrl;

    @SerializedName("latitude")
    String mLatitude;

    @SerializedName("longitude")
    String mLongitude;

    @SerializedName("location")
    Location mLocation;

    @SerializedName("map_image")
    String mMapImage;

    @SerializedName("total_launch_count")
    int mTotalLaunchCounter;

    public Pad(int id, int agencyId, String name, String infoUrl, String wikipedia,
               String mapUrl, String latitude, String longitude, Location location,
               String mapImage, int totalLaunchCounter) {

        this.mId = id;
        this.mAgencyId = agencyId;
        this.mName = name;
        this.mInfoUrl = infoUrl;
        this.mWikipedia = wikipedia;
        this.mMapUrl = mapUrl;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mLocation = location;
        this.mMapImage = mapImage;
        this.mTotalLaunchCounter = totalLaunchCounter;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public int getAgencyId() {

        return mAgencyId;
    }

    public void setAgencyId(int agencyId) {

        this.mAgencyId = agencyId;
    }

    public String getName() {

        return mName;
    }

    public void setName(String name) {

        this.mName = name;
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

    public String getMapUrl() {

        return mMapUrl;
    }

    public void setMapUrl(String mapUrl) {

        this.mMapUrl = mapUrl;
    }

    public String getLatitude() {

        return mLatitude;
    }

    public void setLatitude(String latitude) {

        this.mLatitude = latitude;
    }

    public String getLongitude() {

        return mLongitude;
    }

    public void setLongitude(String longitude) {

        this.mLongitude = longitude;
    }

    public Location getLocation() {

        return mLocation;
    }

    public void setLocation(Location location) {

        this.mLocation = location;
    }

    public String getMapImage() {

        return mMapImage;
    }

    public void setMapImage(String mapImage) {

        this.mMapImage = mapImage;
    }

    public int getTotalLaunchCounter() {

        return mTotalLaunchCounter;
    }

    public void setTotalLaunchCounter(int totalLaunchCounter) {

        this.mTotalLaunchCounter = totalLaunchCounter;
    }
}
