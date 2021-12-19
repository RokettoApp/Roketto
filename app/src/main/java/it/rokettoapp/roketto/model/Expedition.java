package it.rokettoapp.roketto.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "expedition")
public class Expedition implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("start")
    private Date mStart;

    @SerializedName("end")
    private Date mEnd;

    @SerializedName("spacestation")
    private SpaceStation mSpaceStation;

    @SerializedName("crew")
    private List<AstronautFlight> mCrew;

    @SerializedName("mission_patches")
    private List<MissionPatch> mMissionPatches;

    @Ignore
    public Expedition(int id) {

        this.mId = id;
    }

    public Expedition(int id, String name, Date start, Date end, List<AstronautFlight> crew,
                      List<MissionPatch> missionPatches) {

        this.mId = id;
        this.mName = name;
        this.mStart = start;
        this.mEnd = end;
        this.mCrew = crew;
        this.mMissionPatches = missionPatches;
    }

    @Ignore
    public Expedition(int id, String name, Date start, Date end, SpaceStation spaceStation,
                      List<AstronautFlight> crew, List<MissionPatch> missionPatches) {

        this.mId = id;
        this.mName = name;
        this.mStart = start;
        this.mEnd = end;
        this.mSpaceStation = spaceStation;
        this.mCrew = crew;
        this.mMissionPatches = missionPatches;
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

    public Date getStart() {

        return mStart;
    }

    public void setStart(Date start) {

        this.mStart = start;
    }

    public Date getEnd() {

        return mEnd;
    }

    public void setEnd(Date end) {

        this.mEnd = end;
    }

    public SpaceStation getSpaceStation() {

        return mSpaceStation;
    }

    public void setSpaceStation(SpaceStation spaceStation) {

        this.mSpaceStation = spaceStation;
    }

    public List<AstronautFlight> getCrew() {

        return mCrew;
    }

    public void setCrew(List<AstronautFlight> crew) {

        this.mCrew = crew;
    }

    public List<MissionPatch> getMissionPatches() {

        return mMissionPatches;
    }

    public void setMissionPatches(List<MissionPatch> missionPatches) {

        this.mMissionPatches = missionPatches;
    }
}
