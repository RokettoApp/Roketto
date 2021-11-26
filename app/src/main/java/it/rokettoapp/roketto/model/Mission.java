package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class Mission {

    @SerializedName("id")
    int mId;

    @SerializedName("name")
    String mName;

    @SerializedName("description")
    String mDescription;

    @SerializedName("launch_designator")
    String mLaunchDesignator;

    @SerializedName("type")
    String mType;

    @SerializedName("orbit")
    Orbit mOrbit;

    public Mission(int id, String name, String description, String launchDesignator, String type,
                   Orbit orbit) {

        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mLaunchDesignator = launchDesignator;
        this.mType = type;
        this.mOrbit = orbit;
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

    public String getDescription() {

        return mDescription;
    }

    public void setDescription(String description) {

        this.mDescription = description;
    }

    public String getLaunchDesignator() {

        return mLaunchDesignator;
    }

    public void setLaunchDesignator(String launchDesignator) {

        this.mLaunchDesignator = launchDesignator;
    }

    public String getType() {

        return mType;
    }

    public void setType(String type) {

        this.mType = type;
    }

    public Orbit getOrbit() {

        return mOrbit;
    }

    public void setOrbit(Orbit orbit) {

        this.mOrbit = orbit;
    }
}
