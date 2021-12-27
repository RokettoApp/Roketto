package it.rokettoapp.roketto.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(tableName = "mission")
public class Mission implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("launch_designator")
    private String mLaunchDesignator;

    @SerializedName("type")
    private String mType;

    @Embedded(prefix = "orbit_")
    @SerializedName("orbit")
    private Orbit mOrbit;

    @Ignore
    public Mission(int id) {

        this.mId = id;
    }

    public Mission(int id, String name, String description, String launchDesignator, String type,
                   Orbit orbit) {

        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mLaunchDesignator = launchDesignator;
        this.mType = type;
        this.mOrbit = orbit;
    }

    public static Mission buildMinMission(Mission mission) {

        Mission minMission = new Mission(mission.getId());
        minMission.setDescription(mission.getDescription());
        minMission.setType(mission.getType());
        minMission.setOrbit(Orbit.buildMinOrbit(mission.getOrbit()));
        return minMission;
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
