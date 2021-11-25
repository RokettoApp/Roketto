package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class SpacecraftFlight {

    @SerializedName("id")
    int mId;

    @SerializedName("mission_end")
    Date mMissionEnd;

    @SerializedName("destination")
    String mDestination;

    @SerializedName("launch_crew")
    List<AstronautFlight> mLaunchCrew;

    @SerializedName("onboard_crew")
    List<AstronautFlight> mOnboardCrew;

    @SerializedName("landing_crew")
    List<AstronautFlight> mLandingCrew;

    @SerializedName("spacecraft")
    Spacecraft mSpacecraft;

    // TODO:
    // @SerializedName("launch")
    // Launch mLaunch

    // TODO:
    // @SerializedName("docking_events")
    // DockingEvent mDockingevent

    public SpacecraftFlight(int id, Date missionEnd, String destination,
                            List<AstronautFlight> launchCrew, List<AstronautFlight> onboardCrew,
                            List<AstronautFlight> landingCrew, Spacecraft spacecraft) {

        this.mId = id;
        this.mDestination = destination;
        this.mMissionEnd = missionEnd;
        this.mLaunchCrew = launchCrew;
        this.mOnboardCrew = onboardCrew;
        this.mLandingCrew = landingCrew;
        this.mSpacecraft = spacecraft;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public String getDestination() {

        return mDestination;
    }

    public void setDestination(String destination) {

        this.mDestination = destination;
    }

    public Date getMissionEnd() {

        return mMissionEnd;
    }

    public void setMissionEnd(Date missionEnd) {

        this.mMissionEnd = missionEnd;
    }

    public List<AstronautFlight> getLaunchCrew() {

        return mLaunchCrew;
    }

    public void setLaunchCrew(List<AstronautFlight> launchCrew) {

        this.mLaunchCrew = launchCrew;
    }

    public List<AstronautFlight> gemOnboardCrew() {

        return mOnboardCrew;
    }

    public void setOnboardCrew(List<AstronautFlight> onboardCrew) {

        this.mOnboardCrew = onboardCrew;
    }

    public List<AstronautFlight> getLandingCrew() {

        return mLandingCrew;
    }

    public void setLandingCrew(List<AstronautFlight> landingCrew) {

        this.mLandingCrew = landingCrew;
    }

    public Spacecraft getSpacecraft() {

        return mSpacecraft;
    }

    public void setSpacecraft(Spacecraft spacecraft) {

        this.mSpacecraft = spacecraft;
    }
}
