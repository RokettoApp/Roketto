package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Expedition {

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
    private List<Astronaut> mCrew;

    @SerializedName("mission_patches")
    private List<MissionPatch> mMissionPatches;

    public Expedition(int id, String name, Date start, Date end, SpaceStation spaceStation,
                      List<Astronaut> crew, List<MissionPatch> missionPatches) {

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

    public List<Astronaut> getCrew() {

        return mCrew;
    }

    public void setCrew(List<Astronaut> crew) {

        this.mCrew = crew;
    }

    public List<MissionPatch> getMissionPatches() {

        return mMissionPatches;
    }

    public void setMissionPatches(List<MissionPatch> missionPatches) {

        this.mMissionPatches = missionPatches;
    }
}
