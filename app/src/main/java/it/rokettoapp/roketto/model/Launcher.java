package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Launcher implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("flight_proven")
    private boolean mFlightProven;

    @SerializedName("serial_number")
    private String mSerialNumber;

    @SerializedName("status")
    private String mStatus;

    @SerializedName("details")
    private String mDetails;

    @SerializedName("launcher_config")
    private LauncherConfig mLauncherConfig;

    @SerializedName("image_url")
    private String mImageUrl;

    @SerializedName("successful_landings")
    private String mSuccessfullandings;

    @SerializedName("attempted_landings")
    private String mAttemptedLandings;

    @SerializedName("flights")
    private String mFlights;

    @SerializedName("last_launch_date")
    private String mLastLaunchDate;

    @SerializedName("first_launch_date")
    private String mFirstLaunchDate;

    public Launcher(int id, boolean flightProven, String serialNumber, String status,
                    String details, LauncherConfig launcherConfig, String imageUrl,
                    String successfullandings, String attemptedLandings, String flights,
                    String lastLaunchDate, String firstLaunchDate) {

        this.mId = id;
        this.mFlightProven = flightProven;
        this.mSerialNumber = serialNumber;
        this.mStatus = status;
        this.mDetails = details;
        this.mLauncherConfig = launcherConfig;
        this.mImageUrl = imageUrl;
        this.mSuccessfullandings = successfullandings;
        this.mAttemptedLandings = attemptedLandings;
        this.mFlights = flights;
        this.mLastLaunchDate = lastLaunchDate;
        this.mFirstLaunchDate = firstLaunchDate;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public boolean isFlightProven() {

        return mFlightProven;
    }

    public void setFlightProven(boolean flightProven) {

        this.mFlightProven = flightProven;
    }

    public String getSerialNumber() {

        return mSerialNumber;
    }

    public void setSerialNumber(String serialNumber) {

        this.mSerialNumber = serialNumber;
    }

    public String getStatus() {

        return mStatus;
    }

    public void setStatus(String status) {

        this.mStatus = status;
    }

    public String getDetails() {

        return mDetails;
    }

    public void setDetails(String details) {

        this.mDetails = details;
    }

    public LauncherConfig getLauncherConfig() {

        return mLauncherConfig;
    }

    public void setLauncherConfig(LauncherConfig launcherConfig) {

        this.mLauncherConfig = launcherConfig;
    }

    public String getImageUrl() {

        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.mImageUrl = imageUrl;
    }

    public String getSuccessfullandings() {

        return mSuccessfullandings;
    }

    public void setSuccessfullandings(String successfullandings) {

        this.mSuccessfullandings = successfullandings;
    }

    public String getAttemptedLandings() {

        return mAttemptedLandings;
    }

    public void setAttemptedLandings(String attemptedLandings) {

        this.mAttemptedLandings = attemptedLandings;
    }

    public String getFlights() {

        return mFlights;
    }

    public void setFlights(String flights) {

        this.mFlights = flights;
    }

    public String getLastLaunchDate() {

        return mLastLaunchDate;
    }

    public void setLastLaunchDate(String lastLaunchDate) {

        this.mLastLaunchDate = lastLaunchDate;
    }

    public String getFirstLaunchDate() {

        return mFirstLaunchDate;
    }

    public void setFirstLaunchDate(String firstLaunchDate) {

        this.mFirstLaunchDate = firstLaunchDate;
    }
}
