package it.rokettoapp.roketto.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "mission_patch")
public class MissionPatch implements Serializable {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private String mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("priority")
    private int mPriority;

    @SerializedName("image_url")
    private String mImageUrl;

    @SerializedName("agency")
    private Agency mAgency;

    @Ignore
    public MissionPatch(String id) {
        this.mId = id;
    }

    public MissionPatch(String id, String name, int priority, String imageUrl, Agency agency) {

        this.mId = id;
        this.mName = name;
        this.mPriority = priority;
        this.mImageUrl = imageUrl;
        this.mAgency = agency;
    }

    public String getId() {

        return mId;
    }

    public void setId(String id) {

        this.mId = id;
    }

    public String getName() {

        return mName;
    }

    public void setName(String name) {

        this.mName = name;
    }

    public int getPriority() {

        return mPriority;
    }

    public void setPriority(int priority) {

        this.mPriority = priority;
    }

    public String getImageUrl() {

        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.mImageUrl = imageUrl;
    }

    public Agency getAgency() {

        return mAgency;
    }

    public void setAgency(Agency agency) {

        this.mAgency = agency;
    }
}
