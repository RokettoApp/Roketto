package it.rokettoapp.roketto.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "spacestation")
public class SpaceStation implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @Embedded(prefix = "status_")
    @SerializedName("status")
    private SpaceStationStatus mSpaceStationStatus;

    @Embedded(prefix = "type_")
    @SerializedName("type")
    private SpaceStationType mSpaceStationType;

    @SerializedName("founded")
    private Date mFounded;

    @SerializedName("deorbited")
    private Date mDeorbited;

    @SerializedName("height")
    private float mHeight;

    @SerializedName("width")
    private float mWidth;

    @SerializedName("mass")
    private float mMass;

    @SerializedName("volume")
    private int mVolume;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("orbit")
    private String mOrbit;

    @SerializedName("onboard_crew")
    private String mOnboardCrew;

    @SerializedName("owners")
    private List<Agency> mOwners;

    @SerializedName("active_expeditions")
    private List<Expedition> mActiveExpeditionList;

    @SerializedName("docking_location")
    private List<DockingLocation> mDockingLocationList;

    @SerializedName("image_url")
    private String mImageUrl;

    @Ignore
    public SpaceStation(int id) {

        this.mId = id;
    }

    public SpaceStation(int id, String name, SpaceStationStatus spaceStationStatus,
                        SpaceStationType spaceStationType, Date founded, Date deorbited,
                        float height, float width, float mass, int volume, String description,
                        String orbit, String onboardCrew, List<Agency> owners,
                        List<Expedition> activeExpeditionList,
                        List<DockingLocation> dockingLocationList, String imageUrl) {

        this.mId = id;
        this.mName = name;
        this.mSpaceStationStatus = spaceStationStatus;
        this.mSpaceStationType = spaceStationType;
        this.mFounded = founded;
        this.mDeorbited = deorbited;
        this.mHeight = height;
        this.mWidth = width;
        this.mMass = mass;
        this.mVolume = volume;
        this.mDescription = description;
        this.mOrbit = orbit;
        this.mOnboardCrew = onboardCrew;
        this.mOwners = owners;
        this.mActiveExpeditionList = activeExpeditionList;
        this.mDockingLocationList = dockingLocationList;
        this.mImageUrl = imageUrl;
    }

    public static SpaceStation buildMinSpaceStation(SpaceStation spaceStation) {

        SpaceStation minSpaceStation = new SpaceStation(spaceStation.getId());
        minSpaceStation.setName(spaceStation.getName());
        minSpaceStation.setDescription(spaceStation.getDescription());
        minSpaceStation.setSpaceStationStatus(spaceStation.getSpaceStationStatus());
        return minSpaceStation;
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

    public SpaceStationStatus getSpaceStationStatus() {

        return mSpaceStationStatus;
    }

    public void setSpaceStationStatus(SpaceStationStatus spaceStationStatus) {

        this.mSpaceStationStatus = spaceStationStatus;
    }

    public SpaceStationType getSpaceStationType() {

        return mSpaceStationType;
    }

    public void setSpaceStationType(SpaceStationType spaceStationType) {

        this.mSpaceStationType = spaceStationType;
    }

    public Date getFounded() {

        return mFounded;
    }

    public void setFounded(Date founded) {

        this.mFounded = founded;
    }

    public Date getDeorbited() {

        return mDeorbited;
    }

    public void setDeorbited(Date deorbited) {

        this.mDeorbited = deorbited;
    }

    public float getHeight() {

        return mHeight;
    }

    public void setHeight(float height) {

        this.mHeight = height;
    }

    public float getWidth() {

        return mWidth;
    }

    public void setWidth(float width) {

        this.mWidth = width;
    }

    public float getMass() {

        return mMass;
    }

    public void setMass(float mass) {

        this.mMass = mass;
    }

    public int getVolume() {

        return mVolume;
    }

    public void setVolume(int volume) {

        this.mVolume = volume;
    }

    public String getDescription() {

        return mDescription;
    }

    public void setDescription(String description) {

        this.mDescription = description;
    }

    public String getOrbit() {

        return mOrbit;
    }

    public void setOrbit(String orbit) {

        this.mOrbit = orbit;
    }

    public String getOnboardCrew() {

        return mOnboardCrew;
    }

    public void setOnboardCrew(String onboardCrew) {

        this.mOnboardCrew = onboardCrew;
    }

    public List<Agency> getOwners() {

        return mOwners;
    }

    public void setOwners(List<Agency> owners) {

        this.mOwners = owners;
    }

    public List<Expedition> getActiveExpeditionList() {

        return mActiveExpeditionList;
    }

    public void setActiveExpeditionList(List<Expedition> activeExpeditionList) {

        this.mActiveExpeditionList = activeExpeditionList;
    }

    public List<DockingLocation> getDockingLocationList() {

        return mDockingLocationList;
    }

    public void setDockingLocationList(List<DockingLocation> dockingLocationList) {

        this.mDockingLocationList = dockingLocationList;
    }

    public String getImageUrl() {

        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.mImageUrl = imageUrl;
    }
}
