package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class DockingLocation {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("docked")
    private DockingEvent mDockingEvent;

    public DockingLocation(int id, String name, DockingEvent dockingEvent) {

        this.mId = id;
        this.mName = name;
        this.mDockingEvent = dockingEvent;
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

    public DockingEvent getDockingEvent() {

        return mDockingEvent;
    }

    public void setDockingEvent(DockingEvent dockingEvent) {

        this.mDockingEvent = dockingEvent;
    }
}
