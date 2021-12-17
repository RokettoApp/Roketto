package it.rokettoapp.roketto.model;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Location {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("country_code")
    private String mCountryCode;

    @SerializedName("map_image")
    private String mMapImage;

    @SerializedName("total_launch_count")
    private String mTotalLaunchCount;

    @SerializedName("total_landing_count")
    private String mTotalLandingCount;

    @SerializedName("pads")
    private List<Pad> mPads;

    @Ignore
    public Location(int id) {

        this.mId = id;
    }

    public Location(int id, String name, String countryCode, String mapImage,
                    String totalLaunchCount, String totalLandingCount, List<Pad> pads) {

        this.mId = id;
        this.mName = name;
        this.mCountryCode = countryCode;
        this.mMapImage = mapImage;
        this.mTotalLaunchCount = totalLaunchCount;
        this.mTotalLandingCount = totalLandingCount;
        this.mPads = pads;
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

    public String getCountryCode() {

        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {

        this.mCountryCode = countryCode;
    }

    public String getMapImage() {

        return mMapImage;
    }

    public void setMapImage(String mapImage) {

        this.mMapImage = mapImage;
    }

    public String getTotalLaunchCount() {

        return mTotalLaunchCount;
    }

    public void setTotalLaunchCount(String totalLaunchCount) {

        this.mTotalLaunchCount = totalLaunchCount;
    }

    public String getTotalLandingCount() {

        return mTotalLandingCount;
    }

    public void setTotalLandingCount(String totalLandingCount) {

        this.mTotalLandingCount = totalLandingCount;
    }

    public List<Pad> getPads() {

        return mPads;
    }

    public void setPads(List<Pad> pads) {

        this.mPads = pads;
    }
}
