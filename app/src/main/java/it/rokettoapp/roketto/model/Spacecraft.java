package it.rokettoapp.roketto.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "favorite_spacecraft")
public class Spacecraft {

    @PrimaryKey
    @SerializedName("id")
    int mId;

    @SerializedName("name")
    String mName;

    @SerializedName("serial_number")
    String mSerialNumber;

    @Embedded(prefix = "status_")
    @SerializedName("status")
    SpacecraftStatus mStatus;

    @SerializedName("description")
    String mDescription;

    @Embedded(prefix = "config_")
    @SerializedName("spacecraft_config")
    SpacecraftConfigurationDetail mSpacecraftConfigurationDetail;

    @Ignore
    @SerializedName("flights")
    List<SpacecraftFlight> mSpacecraftFlight;

    public Spacecraft(int id, String name, String serialNumber, SpacecraftStatus status,
                      String description, List<SpacecraftFlight> spacecraftFlight,
                      SpacecraftConfigurationDetail spacecraftConfigurationDetail) {

        this.mId = id;
        this.mName = name;
        this.mSerialNumber = serialNumber;
        this.mStatus = status;
        this.mDescription = description;
        this.mSpacecraftFlight = spacecraftFlight;
        this.mSpacecraftConfigurationDetail = spacecraftConfigurationDetail;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getSerialNumber() {
        return mSerialNumber;
    }

    public void setSerialNumber(String serialNumber) {

        this.mSerialNumber = serialNumber;
    }

    public SpacecraftStatus getStatus() {
        return mStatus;
    }

    public void setStatus(SpacecraftStatus status) {

        this.mStatus = status;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {

        this.mDescription = description;
    }

    public SpacecraftConfigurationDetail getSpacecraftConfigurationDetail() {

        return mSpacecraftConfigurationDetail;
    }

    public void setSpacecraftConfigurationDetail(SpacecraftConfigurationDetail spacecraftConfDetail) {

        this.mSpacecraftConfigurationDetail = spacecraftConfDetail;
    }

    public List<SpacecraftFlight> getSpacecraftFlight() {

        return mSpacecraftFlight;
    }

    public void setSpacecraftFlight(List<SpacecraftFlight> spacecraftFlight) {

        this.mSpacecraftFlight = spacecraftFlight;
    }
}
