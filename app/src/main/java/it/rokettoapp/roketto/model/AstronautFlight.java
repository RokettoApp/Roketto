package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AstronautFlight implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("role")
    private AstronautRole mRole;

    @SerializedName("astronaut")
    private Astronaut mAstronaut;

    public AstronautFlight(int id, AstronautRole role, Astronaut astronaut) {

        this.mId = id;
        this.mRole = role;
        this.mAstronaut = astronaut;
    }

    public int getmId() {

        return mId;
    }

    public void setmId(int id) {

        this.mId = id;
    }

    public AstronautRole getmRole() {

        return mRole;
    }

    public void setmRole(AstronautRole role) {

        this.mRole = role;
    }

    public Astronaut getmAstronaut() {

        return mAstronaut;
    }

    public void setmAstronaut(Astronaut astronaut) {

        this.mAstronaut = astronaut;
    }
}
