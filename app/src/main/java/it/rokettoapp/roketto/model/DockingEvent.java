package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DockingEvent {

    @SerializedName("id")
    int mId;

    @SerializedName("launch_id")
    String mLaunchId;

    @SerializedName("docking")
    Date mDocking;

    @SerializedName("departure")
    Date mDeparture;

    @SerializedName("flight_vehicle")
    SpacecraftFlight mFlightVehicle;

    @SerializedName("docking_location")
    DockingLocation mDockingLocation;

    // TODO:
    // @SerializedName("space_station")
    // SpaceStation mSpaceStation;

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
