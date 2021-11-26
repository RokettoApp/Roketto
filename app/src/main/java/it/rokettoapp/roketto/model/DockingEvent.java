package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DockingEvent {

    @SerializedName("id")
    private int mId;

    @SerializedName("launch_id")
    private String mLaunchId;

    @SerializedName("docking")
    private Date mDocking;

    @SerializedName("departure")
    private Date mDeparture;

    @SerializedName("flight_vehicle")
    private SpacecraftFlight mFlightVehicle;

    @SerializedName("docking_location")
    private DockingLocation mDockingLocation;

    // TODO:
    // @SerializedName("space_station")
    // private SpaceStation mSpaceStation;

    public DockingEvent(int id, String launchId, Date docking, Date departure,
                        SpacecraftFlight flightVehicle, DockingLocation dockingLocation) {

        this.mId = id;
        this.mLaunchId = launchId;
        this.mDocking = docking;
        this.mDeparture = departure;
        this.mFlightVehicle = flightVehicle;
        this.mDockingLocation = dockingLocation;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public String getLaunchId() {

        return mLaunchId;
    }

    public void setLaunchId(String launchId) {

        this.mLaunchId = launchId;
    }

    public Date getDocking() {

        return mDocking;
    }

    public void setDocking(Date docking) {

        this.mDocking = docking;
    }

    public Date getDeparture() {

        return mDeparture;
    }

    public void setDeparture(Date departure) {

        this.mDeparture = departure;
    }

    public SpacecraftFlight getFlightVehicle() {

        return mFlightVehicle;
    }

    public void setFlightVehicle(SpacecraftFlight flightVehicle) {

        this.mFlightVehicle = flightVehicle;
    }

    public DockingLocation getDockingLocation() {

        return mDockingLocation;
    }

    public void setDockingLocation(DockingLocation dockingLocation) {

        this.mDockingLocation = dockingLocation;
    }
}
