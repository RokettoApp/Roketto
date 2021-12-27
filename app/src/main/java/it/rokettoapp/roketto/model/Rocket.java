package it.rokettoapp.roketto.model;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Rocket implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("configuration")
    private LauncherConfig mConfiguration;

    @SerializedName("launcher_stage")
    private List<FirstStage> mLauncherStage;

    @SerializedName("spacecraft_stage")
    private SpacecraftFlight mSpacecraftStage;

    @Ignore
    public Rocket(int id) {

        this.mId = id;
    }

    public Rocket(int id, LauncherConfig configuration, List<FirstStage> launcherStage,
                  SpacecraftFlight spacecraftStage) {

        this.mId = id;
        this.mConfiguration = configuration;
        this.mLauncherStage = launcherStage;
        this.mSpacecraftStage = spacecraftStage;
    }

    public static Rocket buildMinRocket(Rocket rocket) {

        Rocket minRocket = new Rocket(rocket.getId());
        if (rocket.getSpacecraftStage() != null) {
            minRocket.setSpacecraftStage(SpacecraftFlight
                    .buildMinSpacecraftFlight(rocket.getSpacecraftStage()));
        }
        minRocket.setConfiguration(LauncherConfig.buildMinLauncherConfig(rocket.getConfiguration()));
        return minRocket;
    }

    public int getId() {
        
        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public LauncherConfig getConfiguration() {

        return mConfiguration;
    }

    public void setConfiguration(LauncherConfig configuration) {

        this.mConfiguration = configuration;
    }

    public List<FirstStage> getLauncherStage() {

        return mLauncherStage;
    }

    public void setLauncherStage(List<FirstStage> launcherStage) {

        this.mLauncherStage = launcherStage;
    }

    public SpacecraftFlight getSpacecraftStage() {

        return mSpacecraftStage;
    }

    public void setSpacecraftStage(SpacecraftFlight spacecraftStage) {

        this.mSpacecraftStage = spacecraftStage;
    }
}
