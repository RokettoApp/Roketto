package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DockingLocation implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("docked")
    private DockingEvent mDockingLocation;

    public DockingLocation(int id, String name, DockingEvent dockingLocation) {

        this.mId = id;
        this.mName = name;
        this.mDockingLocation = dockingLocation;
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

    public DockingEvent getDockingLocation() {

        return mDockingLocation;
    }

    public void setDockingLocationList(DockingEvent dockingLocation) {

        this.mDockingLocation = dockingLocation;
    }
}
