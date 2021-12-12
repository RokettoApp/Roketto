package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FirstStage implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("type")
    private String mType;

    @SerializedName("reused")
    private boolean mReused;

    @SerializedName("launcher_flight_number")
    private String mLauncherFlightNumber;

    @SerializedName("launcher")
    private Launcher mLauncher;

    @SerializedName("landing")
    private Landing mLanding;

    @SerializedName("previous_flight_date")
    private String mPreviousFlightDate;

    @SerializedName("turn_around_time_days")
    private String mTurnAroundTimeDays;

    @SerializedName("previous_flight")
    private Launch mPreviousFlight;

    public FirstStage(int id, String type, boolean reused, String launcherFlightNumber,
                      Launcher launcher, Landing landing, String previousFlightDate,
                      String turnAroundTimeDays, Launch previousFlight) {

        this.mId = id;
        this.mType = type;
        this.mReused = reused;
        this.mLauncherFlightNumber = launcherFlightNumber;
        this.mLauncher = launcher;
        this.mLanding = landing;
        this.mPreviousFlightDate = previousFlightDate;
        this.mTurnAroundTimeDays = turnAroundTimeDays;
        this.mPreviousFlight = previousFlight;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public String getType() {

        return mType;
    }

    public void setType(String type) {

        this.mType = type;
    }

    public boolean isReused() {

        return mReused;
    }

    public void setReused(boolean reused) {

        this.mReused = reused;
    }

    public String getLauncherFlightNumber() {

        return mLauncherFlightNumber;
    }

    public void setLauncherFlightNumber(String launcherFlightNumber) {

        this.mLauncherFlightNumber = launcherFlightNumber;
    }

    public Launcher getLauncher() {

        return mLauncher;
    }

    public void setLauncher(Launcher launcher) {

        this.mLauncher = launcher;
    }

    public Landing getLanding() {

        return mLanding;
    }

    public void setLanding(Landing landing) {

        this.mLanding = landing;
    }

    public String getPreviousFlightDate() {

        return mPreviousFlightDate;
    }

    public void setPreviousFlightDate(String previousFlightDate) {

        this.mPreviousFlightDate = previousFlightDate;
    }

    public String getTurnAroundTimeDays() {

        return mTurnAroundTimeDays;
    }

    public void setTurnAroundTimeDays(String turnAroundTimeDays) {

        this.mTurnAroundTimeDays = turnAroundTimeDays;
    }

    public Launch getPreviousFlight() {

        return mPreviousFlight;
    }

    public void setPreviousFlight(Launch previousFlight) {

        this.mPreviousFlight = previousFlight;
    }
}
