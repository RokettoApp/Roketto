package it.rokettoapp.roketto.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

@Entity(tableName = "spacecraft_flight")
public class SpacecraftFlight {

    @PrimaryKey
    @SerializedName("id")
    private int mId;

    @SerializedName("mission_end")
    private Date mMissionEnd;

    @SerializedName("destination")
    private String mDestination;

    @SerializedName("launch_crew")
    private List<AstronautFlight> mLaunchCrew;

    @SerializedName("onboard_crew")
    private List<AstronautFlight> mOnboardCrew;

    @SerializedName("landing_crew")
    private List<AstronautFlight> mLandingCrew;

    @SerializedName("spacecraft")
    private Spacecraft mSpacecraft;

    @SerializedName("launch")
    private Launch mLaunch;

    @SerializedName("docking_events")
    private List<DockingEvent> mDockingEvent;

    @Ignore
    public SpacecraftFlight(int id) {

        this.mId = id;
    }

    public SpacecraftFlight(int id, Date missionEnd, String destination,
                            List<AstronautFlight> launchCrew, List<AstronautFlight> onboardCrew,
                            List<AstronautFlight> landingCrew, Spacecraft spacecraft,
                            Launch launch, List<DockingEvent> dockingEvent) {

        this.mId = id;
        this.mDestination = destination;
        this.mMissionEnd = missionEnd;
        this.mLaunchCrew = launchCrew;
        this.mOnboardCrew = onboardCrew;
        this.mLandingCrew = landingCrew;
        this.mSpacecraft = spacecraft;
        this.mLaunch = launch;
        this.mDockingEvent = dockingEvent;
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

    public List<AstronautFlight> getOnboardCrew() {

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

    public Launch getLaunch() {

        return mLaunch;
    }

    public void setLaunch(Launch launch) {

        this.mLaunch = launch;
    }

    public List<DockingEvent> getDockingEvent() {

        return mDockingEvent;
    }

    public void setDockingEvent(List<DockingEvent> dockingEvent) {

        this.mDockingEvent = dockingEvent;
    }
}
