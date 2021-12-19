package it.rokettoapp.roketto.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName = "docking_event")
public class DockingEvent implements Serializable {

    @PrimaryKey
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

    @SerializedName("space_station")
    private SpaceStation mSpaceStation;

    @Ignore
    public DockingEvent(int id) {

        this.mId = id;
    }

    public DockingEvent(int id, String launchId, Date docking, Date departure,
                        SpacecraftFlight flightVehicle, DockingLocation dockingLocation,
                        SpaceStation spaceStation) {

        this.mId = id;
        this.mLaunchId = launchId;
        this.mDocking = docking;
        this.mDeparture = departure;
        this.mFlightVehicle = flightVehicle;
        this.mDockingLocation = dockingLocation;
        this.mSpaceStation = spaceStation;
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

    public SpaceStation getSpaceStation() {

        return mSpaceStation;
    }

    public void setSpaceStation(SpaceStation spaceStation) {

        this.mSpaceStation = spaceStation;
    }
}
